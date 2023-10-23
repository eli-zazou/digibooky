package com.switchfully.www.service;

import com.switchfully.www.domain.security.DecodedCredentials;
import com.switchfully.www.domain.security.Feature;
import com.switchfully.www.domain.member.Member;
import com.switchfully.www.exceptions.UnauthorizatedException;
import com.switchfully.www.exceptions.WrongPasswordException;
import com.switchfully.www.repository.MemberRepository;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import static java.lang.String.format;


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
                .orElseThrow(() -> new UnauthorizatedException("Unknown user " + credentials.getEmail()));

        if (!user.doesPasswordMatch(credentials.getPassword())) {
            LOG.errorf("Password does not match for user %s", credentials.getEmail());
            throw new WrongPasswordException("Password does not match for user " + credentials.getEmail());
        }

        if (!user.canHaveAccessTo(feature)) {
            LOG.error(format("User %s does not have access to %s", credentials.getEmail(), feature));
            throw new UnauthorizatedException("User " + credentials.getEmail() + " does not have access to " + feature.toString());
        }

    }

    private DecodedCredentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new DecodedCredentials(username, password);
    }
}