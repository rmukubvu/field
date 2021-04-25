package za.co.amakosifire.field.infrastructure.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import za.co.amakosifire.field.infrastructure.auth.model.VerificationToken;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends MongoRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
