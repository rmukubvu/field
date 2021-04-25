package za.co.amakosifire.field.infrastructure.auth;


import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.auth.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
}
