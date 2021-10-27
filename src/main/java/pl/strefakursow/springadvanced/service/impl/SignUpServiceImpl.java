package pl.strefakursow.springadvanced.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.strefakursow.springadvanced.entity.User;
import pl.strefakursow.springadvanced.repository.UserRepository;
import pl.strefakursow.springadvanced.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(User user) {
        Assert.isNull(user.getIdUser(), "Cant sign up given user, it already has set id" + user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
