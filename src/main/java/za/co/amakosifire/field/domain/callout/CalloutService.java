package za.co.amakosifire.field.domain.callout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.callout.model.AcceptedCallout;
import za.co.amakosifire.field.domain.callout.model.Callout;
import za.co.amakosifire.field.domain.callout.model.CalloutReference;
import za.co.amakosifire.field.domain.review.ReviewService;
import za.co.amakosifire.field.domain.review.model.Review;
import za.co.amakosifire.field.domain.clients.ClientService;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.fleet.FleetService;
import za.co.amakosifire.field.domain.shared.*;
import za.co.amakosifire.field.domain.user.UserService;
import za.co.amakosifire.field.domain.user.model.Photo;
import za.co.amakosifire.field.infrastructure.callout.CalloutRepository;
import za.co.amakosifire.field.infrastructure.review.ReviewRepository;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CalloutService {

    private ReviewService reviewService;
    private CalloutRepository calloutRepository;
    private ClientService clientService;
    private FleetService fleetService;
    private UserService userService;

    @Autowired
    public CalloutService(final ReviewService reviewService,
                          final CalloutRepository calloutRepository,
                          final ClientService clientService,
                          final FleetService fleetService,
                          final UserService userService) {
        this.reviewService = reviewService;
        this.calloutRepository = calloutRepository;
        this.clientService = clientService;
        this.fleetService = fleetService;
        this.userService = userService;
    }

    public CalloutReference createCallout(AcceptedCallout acceptedCallout) {
       var site = clientService.findSiteById(acceptedCallout.getSiteId());
       if (Optional.ofNullable(site).isPresent()) {
           var referenceNumber = referenceNumberFrom(site.getId());
           calloutRepository.save(CalloutMapper.INSTANCE.fromDomain(Callout.builder()
                   .referenceNumber(referenceNumber)
                   .acceptedDate(LocalDateTime.now())
                   .site(site)
                   .calloutPoint(acceptedCallout.getPoint())
                   .callOutDistance(distanceBetween(site, acceptedCallout))
                   .car(carDetailsFrom(acceptedCallout.getUserId()))
                   .photo(photoFrom(acceptedCallout.getUserId()))
                   .rating(reviewService.ratingFrom(acceptedCallout.getUserId()))
                   .user(userService.findUserById(acceptedCallout.getUserId()))
                   .build()));
           return CalloutReference.builder().reference(referenceNumber).build();
       }
       return CalloutReference.builder().reference(null).build();
    }


    public Callout calloutBy(String reference) {
        return CalloutMapper.INSTANCE.toDomain(calloutRepository.findByReferenceNumberEquals(reference));
    }

    private Photo photoFrom(String userId) {
       return userService.getPhotoByUserId(userId);
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
