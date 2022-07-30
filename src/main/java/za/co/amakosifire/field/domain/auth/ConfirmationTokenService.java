package za.co.amakosifire.field.domain.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.auth.model.User;
import za.co.amakosifire.field.infrastructure.auth.VerificationTokenRepository;
import za.co.amakosifire.field.infrastructure.auth.model.VerificationToken;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setConfirmed(false);
        verificationToken.setExpiredAt(addExpiry(new Date(),48));
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public Optional<VerificationToken> getToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    private Date addExpiry(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public void setConfirmed(String token) {
        var record = verificationTokenRepository.findByToken(token);
        record.ifPresent( rec -> {
            rec.setConfirmed(true);
            rec.setConfirmedAt(new Date());
            verificationTokenRepository.save(rec);
        });
    }
}
