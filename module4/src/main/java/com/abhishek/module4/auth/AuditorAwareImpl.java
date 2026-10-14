package com.abhishek.module4.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

// This class to set the createdBy and updatedBy fields
// This will fetch the username from Spring Security
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Just returning a default value because currently we do not have the Spring Security implemented
        return Optional.of("Abhishek Paturkar");
    }
}
