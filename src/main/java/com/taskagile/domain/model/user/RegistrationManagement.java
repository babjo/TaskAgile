package com.taskagile.domain.model.user;

import java.util.Locale;

import org.springframework.stereotype.Component;

import com.taskagile.domain.common.security.PasswordEncryptor;

@Component
public class RegistrationManagement {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public RegistrationManagement(UserRepository repository, PasswordEncryptor passwordEncryptor) {
        this.userRepository = repository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public User register(String username, String emailAddress, String password) throws RegistrationException {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new UsernameExistsException();
        }

        existingUser = userRepository.findByEmailAddress(emailAddress.toLowerCase());
        if (existingUser != null) {
            throw new EmailAddressExistsException();
        }

        String encryptedPassword = passwordEncryptor.encrypt(password);
        User newUser = User.create(username, emailAddress.toLowerCase(), encryptedPassword);
        userRepository.save(newUser);
        return newUser;
    }
}
