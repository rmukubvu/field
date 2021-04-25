package za.co.amakosifire.field.domain.auth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.amakosifire.field.application.dto.AuthenticationResponse;
import za.co.amakosifire.field.application.dto.LoginRequest;
import za.co.amakosifire.field.application.dto.RefreshTokenRequest;
import za.co.amakosifire.field.application.dto.RegisterRequest;
import za.co.amakosifire.field.domain.security.JwtProvider;
import za.co.amakosifire.field.domain.shared.FieldEyeException;
import za.co.amakosifire.field.domain.user.mapper.RoleMapper;
import za.co.amakosifire.field.domain.user.mapper.UserMapper;
import za.co.amakosifire.field.domain.user.model.User;
import za.co.amakosifire.field.infrastructure.auth.VerificationTokenRepository;
import za.co.amakosifire.field.infrastructure.auth.model.VerificationToken;
import za.co.amakosifire.field.infrastructure.user.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    @Value("${phone.country-code}")
    private String COUNTRY_CODE;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public void register(RegisterRequest registerRequest) {
        var user = User.builder()
                .userName(registerRequest.getUsername())
                .contactNumber(registerRequest.getContactNumber())
                .build();
        userRepository.save(UserMapper.INSTANCE.fromDomain(
                user.onSave(passwordEncoder.encode(registerRequest.getPassword()),COUNTRY_CODE)));

//        String token = generateVerificationToken(user);
//        mailService.sendMail(new NotificationEmail("Please Activate your Account",
//                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
//                "please click on the below url to activate your account : " +
//                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return UserMapper.INSTANCE.toDomain(userRepository.findUserByUserNameEquals(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername())));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUserName();
        User user = UserMapper.INSTANCE.toDomain(userRepository.findUserByUserNameEquals(username).orElseThrow(() -> new FieldEyeException("User not found with name - " + username)));
        user.setEnabled(true);
        userRepository.save(UserMapper.INSTANCE.fromDomain(user));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new FieldEyeException("Invalid Token")));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        var token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
