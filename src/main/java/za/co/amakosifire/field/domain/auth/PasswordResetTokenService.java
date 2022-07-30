package za.co.amakosifire.field.domain.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.auth.model.User;
import za.co.amakosifire.field.infrastructure.auth.PasswordTokenRepository;
import za.co.amakosifire.field.infrastructure.auth.VerificationTokenRepository;
import za.co.amakosifire.field.infrastructure.auth.model.PasswordResetToken;
import za.co.amakosifire.field.infrastructure.auth.model.VerificationToken;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PasswordResetTokenService {

    private final PasswordTokenRepository passwordTokenRepository;

    public String generatePasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setChanged(false);
        passwordResetToken.setExpiredAt(addExpiry(new Date(),48));
        passwordTokenRepository.save(passwordResetToken);
        return token;
    }

    public Optional<PasswordResetToken> getToken(String token) {
        return passwordTokenRepository.findByToken(token);
    }

    private Date addExpiry(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public void setChanged(String token) {
        var record = passwordTokenRepository.findByToken(token);
        record.ifPresent( rec -> {
            rec.setChanged(true);
            passwordTokenRepository.save(rec);
        });
    }
}
