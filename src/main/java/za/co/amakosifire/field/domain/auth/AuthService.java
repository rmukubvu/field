package za.co.amakosifire.field.domain.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.application.dto.AuthenticationResponse;
import za.co.amakosifire.field.application.dto.LoginRequest;
import za.co.amakosifire.field.application.dto.RefreshTokenRequest;
import za.co.amakosifire.field.domain.auth.mapper.RoleMapper;
import za.co.amakosifire.field.domain.auth.model.Role;
import za.co.amakosifire.field.domain.security.JwtProvider;
import za.co.amakosifire.field.infrastructure.user.RoleRepository;

import java.time.Instant;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    public Role saveRole(Role role) {
        return RoleMapper.INSTANCE.toDomain(
                roleRepository.save(RoleMapper.INSTANCE.fromDomain(role)));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll().stream().map(RoleMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        var userName = loginRequest.getUsername();
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,
                loginRequest.getPassword()));

        if (!authenticate.isAuthenticated()) return AuthenticationResponse.builder().error(true).build();

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .error(false)
                .refreshToken(refreshTokenService.generateRefreshToken(userName).getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(userName)
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
