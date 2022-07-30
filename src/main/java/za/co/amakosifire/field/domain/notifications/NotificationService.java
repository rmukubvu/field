package za.co.amakosifire.field.domain.notifications;

import com.pusher.pushnotifications.PushNotifications;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.clients.ClientService;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.location.LocationService;
import za.co.amakosifire.field.domain.location.model.Location;
import za.co.amakosifire.field.domain.notifications.model.DeviceMessage;
import za.co.amakosifire.field.domain.notifications.model.Fault;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {

//    @Value("${pusher.instance-key}")
//    private String instanceId;
//    @Value("${pusher.secret-key}")
//    private String secretKey;
    @Value("${pusher.beams.interests.no-technician-found}")
    private String NO_TECHNICIAN_FOUND_INTEREST;
    private PushNotifications beamsClient;
    private ClientService clientService;
    private LocationService locationService;

    @Autowired
    public NotificationService(
            @Value("${pusher.instance-key}") String instanceId,
            @Value("${pusher.secret-key}") String secretKey,
            final ClientService clientService,
            final LocationService locationService) {
        this.clientService = clientService;
        this.locationService = locationService;
        this.beamsClient = new PushNotifications(instanceId, secretKey);
    }

    public void publishMessage(DeviceMessage deviceMessage) {
        var publishRequest = getPublishRequest("Field Eye", deviceMessage.getMessage());
        try {
            beamsClient.publishToInterests(Arrays.asList(deviceMessage.getDeviceId()), publishRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //if cant find the person look for someone who has the same experience as fault.
    public void publishFaultToNearbyTechnicians(Fault fault)  {
        var site = getSiteDetails(fault.getSiteId());
        if (site == null) return;
        var locations = locationService.getDevicesNearFault(site.getPoint().getX(), site.getPoint().getY());
        var publishRequest = getPublishRequest("No Technicians", getMessage(site.getName(), fault.getMessage()));
        //publish to head office - red flag - NO_TECHNICIAN_FOUND_INTEREST
        var interests = CollectionUtils.isEmpty(locations) ? Arrays.asList(NO_TECHNICIAN_FOUND_INTEREST) : getDeviceInterests(locations);
        try {
            beamsClient.publishToInterests(interests, publishRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Site getSiteDetails(String siteId) {
        return clientService.findSiteById(siteId);
    }

    private String getMessage(String siteName,String message){
        return String.format("%s\n%s",siteName,message);
    }

    private List<String> getDeviceInterests(List<Location> location) {
        return location.stream().map(loc -> loc.getDeviceId()).collect(Collectors.toList());
    }

    private Map<String,Map> getPublishRequest(String title,String body) {
        Map<String, Map> publishRequest = new HashMap();
        Map<String, String> fcmNotification = new HashMap();
        fcmNotification.put("title", title);
        fcmNotification.put("body", body);
        Map<String, Map> fcm = new HashMap();
        fcm.put("notification", fcmNotification);
        publishRequest.put("fcm", fcm);
        return publishRequest;
    }

}
