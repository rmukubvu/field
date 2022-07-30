package za.co.amakosifire.field.domain.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.amakosifire.field.application.dto.ChangePasswordRequest;
import za.co.amakosifire.field.application.dto.ForgotPasswordRequest;
import za.co.amakosifire.field.application.dto.ForgotPasswordResponse;
import za.co.amakosifire.field.application.dto.UserRequest;
import za.co.amakosifire.field.domain.auth.mapper.UserMapper;
import za.co.amakosifire.field.domain.auth.model.User;
import za.co.amakosifire.field.domain.email.EmailSender;
import za.co.amakosifire.field.domain.shared.DateUtil;
import za.co.amakosifire.field.domain.shared.FieldEyeException;
import za.co.amakosifire.field.domain.shared.PhoneNumber;
import za.co.amakosifire.field.infrastructure.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailSender emailSender;
    private final PasswordResetTokenService passwordResetTokenService;
    private final PasswordEncoder passwordEncoder;

    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return UserMapper.INSTANCE.toDomain(userRepository.findUserByUserNameEquals(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername())));
    }

    public User findUserById(String id) {
        var user = userRepository.findById(id);
        return user.map(UserMapper.INSTANCE::toDomain).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public void save(User user) {
        userRepository.save(UserMapper.INSTANCE.fromDomain(user));
    }

    public void enableUser(String username) {
        User user = UserMapper.INSTANCE.toDomain(userRepository.findUserByUserNameEquals(username).orElseThrow(() -> new FieldEyeException("User not found with name - " + username)));
        user.setEnabled(true);
        userRepository.save(UserMapper.INSTANCE.fromDomain(user));
    }

    public ForgotPasswordResponse resetPassword(String userName) {
        String response = "Username is invalid";
        var user = getUserBy(userName);
        if (Optional.of(user).isPresent()){
            String token = passwordResetTokenService.generatePasswordResetToken(user);
            response = "A link has been sent to your email to change the password";
            String link = "http://localhost:4200/user/pwd?token=" + token;
            emailSender.send(
                    user.getEmail(),
                    "Change your password",
                    buildEmail(user.getFirstName(), link));
        }
        return ForgotPasswordResponse.builder().message(response).build();
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        if (confirmPasswordToken(request.getToken())) {
            var user = getUserBy(request.getUserName());
            if (Optional.of(user).isPresent()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                userRepository.save(UserMapper.INSTANCE.fromDomain(user));
                passwordResetTokenService.setChanged(request.getToken());
            }
        }
    }

    private boolean confirmPasswordToken(String token) {
        var passwordResetTokenToken = passwordResetTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (passwordResetTokenToken.getChanged()) {
            throw new IllegalStateException("password already changed");
        }

        LocalDateTime expiredAt = DateUtil.toLocalDateTime(passwordResetTokenToken.getExpiredAt());

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        return true;
    }

    public boolean userExists(String userName) {
        return Optional.ofNullable(getUserBy(userName)).isPresent();
    }

    public void updateUser(UserRequest request) {
        var user = getUserBy(request.getUsername());
        if (Optional.ofNullable(user).isPresent()) {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setContactNumber(PhoneNumber.getFormat(request.getContactNumber(), "27"));
            save(user);
        }
    }

    private User getUserBy(String userName) {
        return UserMapper.INSTANCE.toDomain(userRepository.findUserByUserNameEquals(userName)
                .orElse(null));
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Change your password</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Please click on the below link to change your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Change Password</a> </p></blockquote>\n Link will expire in 2 days. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
