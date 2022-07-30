package za.co.amakosifire.field.domain.site;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.shared.PumpMapper;
import za.co.amakosifire.field.domain.shared.SiteConfigurationMapper;
import za.co.amakosifire.field.domain.shared.SiteMapper;
import za.co.amakosifire.field.domain.shared.SiteUserMapper;
import za.co.amakosifire.field.domain.site.model.Pump;
import za.co.amakosifire.field.domain.site.model.SiteConfiguration;
import za.co.amakosifire.field.domain.site.model.SiteUser;
import za.co.amakosifire.field.infrastructure.clients.SiteRepository;
import za.co.amakosifire.field.infrastructure.pumps.PumpRepository;
import za.co.amakosifire.field.infrastructure.site.configuration.SiteConfigurationRepository;
import za.co.amakosifire.field.infrastructure.site.configuration.SiteUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SiteService {

    private PumpRepository pumpRepository;
    private SiteConfigurationRepository siteConfigurationRepository;
    private SiteUserRepository siteUserRepository;
    private SiteRepository siteRepository;

    public SiteUser saveSiteUser(SiteUser siteUser) {
        return SiteUserMapper.INSTANCE.toDomain(
                siteUserRepository.save(SiteUserMapper.INSTANCE.fromDomain(siteUser))
        );
    }

    public SiteConfiguration saveSiteConfiguration(SiteConfiguration siteConfiguration) {
        return SiteConfigurationMapper.INSTANCE.toDomain(
                siteConfigurationRepository.save(SiteConfigurationMapper.INSTANCE.fromDomain(siteConfiguration))
        );
    }

    public Pump savePump(Pump pump) {
        return PumpMapper.INSTANCE.toDomain(
                pumpRepository.save(PumpMapper.INSTANCE.fromDomain(pump))
        );
    }

    public List<Site> sitesForUser(String userId) {
        var listOfSites = new ArrayList<Site>();
        var siteUsers = siteUserRepository.findByUserIdEquals(userId);
        if (CollectionUtils.isNotEmpty(siteUsers)) {
            var siteIds = siteUsers.stream().map(s -> s.getSiteId()).collect(Collectors.toList());
            listOfSites = siteIds.stream().map(id -> SiteMapper.INSTANCE.toDomain(siteRepository.findById(id).get())).collect(Collectors.toCollection(ArrayList::new));
        }
        return listOfSites;
    }

    public List<Pump> pumpsAtSite(String siteId) {
        var listOfPumps = new ArrayList<Pump>();
        var siteConfigurations = siteConfigurationRepository.findBySiteIdEquals(siteId);
        if (CollectionUtils.isNotEmpty(siteConfigurations)) {
            var pumpIds = siteConfigurations.stream().map(s -> s.getPumpId()).collect(Collectors.toList());
            listOfPumps = pumpIds.stream().map(id -> PumpMapper.INSTANCE.toDomain(pumpRepository.findById(id).get())).collect(Collectors.toCollection(ArrayList::new));
        }
        return listOfPumps;
    }

}
