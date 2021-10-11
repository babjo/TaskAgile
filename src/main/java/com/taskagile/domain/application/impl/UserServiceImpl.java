package com.taskagile.domain.application.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.thymeleaf.util.StringUtils;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final RegistrationManagement registrationManagement;
    private final DomainEventPublisher eventPublisher;
    private final MailManager mailManager;
    private final UserRepository userRepository;

    public UserServiceImpl(RegistrationManagement registrationManagement,
                           DomainEventPublisher eventPublisher,
                           MailManager mailManager,
                           UserRepository userRepository) {
        this.registrationManagement = registrationManagement;
        this.eventPublisher = eventPublisher;
        this.mailManager = mailManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("No user found");
        }
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmailAddress(username);
        } else {
            user = userRepository.findByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("No user found by `" + username + "`");
        }
        return new SimpleUser(user);
    }

    @Override
    public void register(RegistrationCommand command) throws RegistrationException {
        Assert.notNull(command, "Parameter `command` must not be null");
        User newUser = registrationManagement.register(
                command.getUsername(),
                command.getEmailAddress(),
                command.getPassword()
        );
        sendWelcomeMessage(newUser);
        eventPublisher.publish(new UserRegisteredEvent(newUser));
    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(user.getEmailAddress(),
                         "Welcome to TaskAgile",
                         "welcome.ftl",
                         MessageVariable.from("user", user));
    }
}
