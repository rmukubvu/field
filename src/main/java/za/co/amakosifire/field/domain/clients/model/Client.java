package za.co.amakosifire.field.domain.clients.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Client implements ModelOnSave<Client> {
    private String id;
    private String name;
    private String accountNumber;
    private String address;
    private Date creationDate;

    @Override
    public Client onSave() {
        this.creationDate = new Date();
        return this;
    }
}
