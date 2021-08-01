package za.co.amakosifire.field.domain.callout;

import lombok.AllArgsConstructor;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.auth.AuthService;
import za.co.amakosifire.field.domain.callout.model.AcceptedCallout;
import za.co.amakosifire.field.domain.callout.model.Callout;
import za.co.amakosifire.field.domain.callout.model.CalloutReference;
import za.co.amakosifire.field.domain.review.ReviewService;
import za.co.amakosifire.field.domain.clients.ClientService;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.fleet.FleetService;
import za.co.amakosifire.field.domain.shared.*;
import za.co.amakosifire.field.domain.image.PhotoService;
import za.co.amakosifire.field.domain.image.model.Photo;
import za.co.amakosifire.field.infrastructure.callout.CalloutRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalloutService {

    private ReviewService reviewService;
    private CalloutRepository calloutRepository;
    private ClientService clientService;
    private FleetService fleetService;
    private PhotoService photoService;
    private AuthService authService;


    public CalloutReference createCallout(AcceptedCallout acceptedCallout) {
       var site = clientService.findSiteById(acceptedCallout.getSiteId());
       if (Optional.ofNullable(site).isPresent()) {
           var referenceNumber = referenceNumberFrom(site.getId());
           calloutRepository.save(CalloutMapper.INSTANCE.fromDomain(Callout.builder()
                   .referenceNumber(referenceNumber)
                   .acceptedDate(new Date())
                   .site(site)
                   .calloutPoint(acceptedCallout.getPoint())
                   .callOutDistance(distanceBetween(site, acceptedCallout))
                   .car(carDetailsFrom(acceptedCallout.getUserId()))
                   .photo(photoFrom(acceptedCallout.getUserId()))
                   .rating(reviewService.ratingFrom(acceptedCallout.getUserId()))
                   .user(authService.findUserById(acceptedCallout.getUserId()))
                   .build()));
           return CalloutReference.builder().reference(referenceNumber).build();
       }
       return CalloutReference.builder().reference(null).build();
    }


    public Callout calloutBy(String reference) {
        return CalloutMapper.INSTANCE.toDomain(calloutRepository.findByReferenceNumberEquals(reference));
    }

    private Photo photoFrom(String userId) {
       return photoService.getPhotoByUserId(userId);
    }

    private String carDetailsFrom(String userId) {
        var fleet = fleetService.fleetByUserId(userId);
        var car = fleetService.fleetModelById(fleet.getFleetModelId());
        return String.format("%s %s\n%s\n%s", car.getMake() ,
                car.getModel() , fleet.getColor() ,fleet.getRegistrationNumber());
    }

    private String distanceBetween(Site site, AcceptedCallout acceptedCallout) {
        var distanceFromSite = distanceBetweenSiteAndTechnician(site.getPoint(), acceptedCallout.getPoint());
        return String.format("%.2f KM", distanceFromSite);
    }

    private double distanceBetweenSiteAndTechnician(Point site,Point technician) {
        return GeometryHelper.distanceBetweenTwoCoordinates(site.getX(), site.getY(), technician.getX(), technician.getY());
    }

    private String referenceNumberFrom(String siteId) {
        int siteCode = siteId.hashCode();
        return String.format("%d-%s", siteCode, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now()));
    }

}
