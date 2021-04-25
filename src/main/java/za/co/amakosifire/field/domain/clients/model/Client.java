package za.co.amakosifire.field.domain.clients.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;

@Data
public class Client implements ModelOnSave<Client> {
    private String id;
    private String name;
    private String accountNumber;
    private String address;
    private LocalDateTime creationDate;

    @Override
    public Client onSave() {
        this.creationDate = LocalDateTime.now();
        return this;
    }
}
