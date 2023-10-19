package com.switchfully.www.service;

import com.switchfully.www.domain.DecodedCredentials;
import com.switchfully.www.domain.Feature;
import com.switchfully.www.domain.Member;
import com.switchfully.www.exceptions.UnauthorizatedException;
import com.switchfully.www.repository.MemberRepository;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.Base64;
import java.util.Optional;

@ApplicationScoped
public class SecurityService {

    private static final Logger LOG = Logger.getLogger(SecurityService.class);

    private final MemberRepository memberRepository;

    public SecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateAuthorization(@Nullable String authorization, Feature feature) {
        DecodedCredentials credentials = getUsernamePassword(Optional.ofNullable(authorization)
                .orElseThrow(() -> new UnauthorizatedException("You do not have authorization")));
        Member user = memberRepository.getByEmail(credentials.getEmail())
                .orElseThrow(() -> throwUserUnknownException(credentials.getUsername()));

        if (!user.doesPasswordMatch(credentials.getPassword())) {
            // LOG.info(illegalArgumentException.getMessage());
            logger.errorf("Password does not match for user %s", credentials.getUsername());
            throw new WrongPasswordException();
        }

        if (!user.canHaveAccessTo(feature)) {
            // LOG.info(illegalArgumentException.getMessage());

            logger.error(format("User %s does not have access to %s", credentials.getUsername(), feature));
            throw new UnauthorizatedException();
        }

    }

    private UnknownUserException throwUserUnknownException(String username) {
        logger.errorf("Unknown user %s", username);
        return new UnknownUserException();
    }

    private DecodedCredentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new DecodedCredentials(username, password);
    }
}