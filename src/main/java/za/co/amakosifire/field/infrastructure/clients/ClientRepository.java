package za.co.amakosifire.field.infrastructure.clients;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.clients.model.Client;

public interface ClientRepository extends MongoRepository<Client,String> {
}
