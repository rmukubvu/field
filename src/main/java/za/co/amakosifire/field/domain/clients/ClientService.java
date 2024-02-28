package za.co.amakosifire.field.domain.clients;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.clients.model.Client;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.shared.ClientMapper;
import za.co.amakosifire.field.domain.shared.SiteMapper;
import za.co.amakosifire.field.infrastructure.clients.ClientRepository;
import za.co.amakosifire.field.infrastructure.clients.SiteRepository;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final SiteRepository siteRepository;

    public Client saveClient(Client client) {
        client.setId(null);
        client.setCreationDate(new Date());
        return ClientMapper.INSTANCE
                .toDomain(clientRepository.save(ClientMapper.INSTANCE.fromDomain(client.onSave())));
    }

    public Site saveSite(Site site) {
        site.setId(null);
        site.setCreationDate(new Date());
        return SiteMapper.INSTANCE
                .toDomain(siteRepository.save(SiteMapper.INSTANCE.fromDomain(site.onSave())));
    }

    public List<Client> getClients() {
        return clientRepository.findAll().stream().map(ClientMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public List<Site> getSitesForClient(String clientId) {
        return siteRepository.findSitesByClientIdEquals(clientId).stream().map(SiteMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public Site findSiteById(String siteId) {
        var site = siteRepository.findById(siteId);
        return site.isPresent() ? SiteMapper.INSTANCE.toDomain(site.get()) : null;
    }

}
