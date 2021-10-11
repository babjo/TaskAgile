package com.taskagile.domain.application;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.RegistrationException;

public interface UserService extends UserDetailsService {
    void register(RegistrationCommand command) throws RegistrationException;
}
