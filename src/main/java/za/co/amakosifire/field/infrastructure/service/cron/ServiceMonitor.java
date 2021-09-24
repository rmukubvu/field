package za.co.amakosifire.field.infrastructure.service.cron;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.amakosifire.field.infrastructure.asset.AssetRepository;
import za.co.amakosifire.field.infrastructure.service.ServiceAuctionRepository;
import za.co.amakosifire.field.infrastructure.service.ServiceNotificationRepository;
import za.co.amakosifire.field.infrastructure.service.MaintenanceRepository;
import za.co.amakosifire.field.infrastructure.service.model.NotificationType;
import za.co.amakosifire.field.infrastructure.service.model.Maintenance;
import za.co.amakosifire.field.infrastructure.service.model.ServiceAuction;
import za.co.amakosifire.field.infrastructure.service.model.ServiceNotification;
import za.co.amakosifire.field.infrastructure.shared.DateUtil;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
@AllArgsConstructor
public class ServiceMonitor {

    private MaintenanceRepository serviceRepository;
    private ServiceNotificationRepository serviceNotificationRepository;
    private ServiceAuctionRepository serviceAuctionRepository;
    private AssetRepository assetRepository;

    private final short DAYS_TO_PUBLISH_TO_AUCTION = 3;

    //[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]
    //<minute> <hour> <day-of-month> <month> <day-of-week> <command>
    @Scheduled(cron = "0 12 * * ?")
    public void checkServices() {
        log.debug("check services cron started");

        val pair = getSearchParams();
        val servicesDue = serviceRepository.findAllByNextServiceYearEqualsAndAndNextServiceMonthEquals(pair.getYear(), pair.getMonth());
        if (!CollectionUtils.isEmpty(servicesDue)) {
            servicesDue.stream().forEach(service -> {
              val nextServiceDate = DateUtil.toLocalDateTime(service.getNextServiceDate());
             //if date is after or equal then shoot a notification
              if (pair.getNextMonth().isAfter(nextServiceDate) || pair.getNextMonth().isEqual(nextServiceDate)) {
                //mark the ones touched
                  if (!service.getThirtyDayNotificationTouched()) {
                      service.setThirtyDayNotificationTouched(true);
                      service.setStatusChangeDate(new Date());
                      serviceRepository.save(service);
                      serviceNotificationRepository.save(ServiceNotification.builder()
                              .assetId(service.getAssetId())
                              .notificationType(NotificationType.THIRTY_DAYS)
                              .createdDate(new Date())
                              .build());
                  }

                  //check if days left is 2
                  // ./mvnw compile quarkus:dev
                  val days = service.getNextServiceDay() - pair.getNextMonth().getDayOfMonth();
                  if ( days == DAYS_TO_PUBLISH_TO_AUCTION ) {
                      //publish to auction
                      service.setThirtyDayNotificationTouched(false);
                      service.setStatusChangeDate(new Date());
                      serviceRepository.save(service);
                      publishToAuction(service);
                      serviceNotificationRepository.save(ServiceNotification.builder()
                              .assetId(service.getAssetId())
                              .notificationType(NotificationType.TWO_DAYS)
                              .createdDate(new Date())
                              .build());
                  }
              }
            });
        }

        log.debug("check services cron finished");
    }

    private void publishToAuction(Maintenance service) {
        val asset = assetRepository.findById(service.getAssetId());
        serviceAuctionRepository.save(ServiceAuction.builder()
                .asset(asset.get())
                .createdDate(new Date())
                .allocated(false)
                .build());
    }

    //we get a pair with next month and year
    // we use it to reduce the search of service
    private Pair getSearchParams() {
        final LocalDateTime start = LocalDateTime.now().plusMonths(1L);
        //get month ahead + 1
        return Pair.builder().month(start.getMonth().getValue())
                .year(start.getYear())
                .nextMonth(start).build();
    }


}
