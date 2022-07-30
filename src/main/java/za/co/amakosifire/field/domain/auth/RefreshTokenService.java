package za.co.amakosifire.field.domain.auth;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.shared.FieldEyeException;
import za.co.amakosifire.field.domain.shared.RefreshTokenMapper;
import za.co.amakosifire.field.infrastructure.auth.RefreshTokenRepository;
import za.co.amakosifire.field.domain.auth.model.RefreshToken;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository tokenRepository;

    public RefreshToken generateRefreshToken(String username) {
       var refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .username(username)
                .creationDate(new Date())
                .build();

        return RefreshTokenMapper.INSTANCE.toDomain(tokenRepository.save(RefreshTokenMapper.INSTANCE.fromDomain(refreshToken)));
    }

    public void deleteRefreshToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    public boolean validateRefreshToken(final String refreshToken) {
        return Optional.ofNullable(validateToken(refreshToken)).isPresent();
    }

    private RefreshToken validateToken(String token) {
        return RefreshTokenMapper.INSTANCE.toDomain(tokenRepository.findByToken(token)
                .orElseThrow(() -> new FieldEyeException("Invalid Token")));
    }

}
