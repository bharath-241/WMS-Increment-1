
package com.company.wms.warehouse.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser register(String username, String password) {

        String normalizedUsername = username.trim();

        if (appUserRepository.existsByUsername(normalizedUsername)) {
            throw new IllegalArgumentException(
                    "Username is already registered");
        }

        AppUser user = new AppUser(
                normalizedUsername,
                passwordEncoder.encode(password),
                "USER"
        );

        return appUserRepository.save(user);
    }

    @Transactional(readOnly = true)
    public AppUser authenticate(String username, String password) {

        AppUser user = appUserRepository
                .findByUsername(username.trim())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid username or password"
                ));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password"
            );
        }

        return user;
    }
}
