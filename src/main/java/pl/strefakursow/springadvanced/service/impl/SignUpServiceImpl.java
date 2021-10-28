package pl.strefakursow.springadvanced.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pl.strefakursow.springadvanced.component.mailer.RandomStringFactory;
import pl.strefakursow.springadvanced.component.mailer.SingUpMailer;
import pl.strefakursow.springadvanced.entity.Role;
import pl.strefakursow.springadvanced.entity.User;
import pl.strefakursow.springadvanced.repository.RoleRepository;
import pl.strefakursow.springadvanced.repository.UserRepository;
import pl.strefakursow.springadvanced.service.SignUpService;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    private static final int TOKEN_LENGTH = 15;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SingUpMailer mailer;
    private RoleRepository roleRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                             SingUpMailer mailer, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailer = mailer;
        this.roleRepository = roleRepository;
    }

    @Override
    public User signUpUser(User user) {
        Assert.isNull(user.getIdUser(), "Can't sign up given user, it already has set id. User: " + user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String token = RandomStringFactory.getRandomString(TOKEN_LENGTH);
        user.setConfirmationToken(token);
        Optional<Role> roleOptional = roleRepository.findByName("USER");
        if (roleOptional.isPresent()) {
            user.getRoles().add(roleOptional.get());
        }
        mailer.sendConfirmationLink(user.getEmail(), token);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

}

