package pl.strefakursow.springadvanced.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomDaoAuthenticationProvider implements AuthenticationProvider {

    private static final String USERNAME_CANNOT_BEN_NULL = "Username cannot ben null";
    private static final String CREDENTIALS_CANNOT_BEN_NULL = "Credentials cannot ben null";
    private static final String INCORRECT_PASSWORD = "Incorrect password";

    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomDaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Assert.notNull(name, USERNAME_CANNOT_BEN_NULL);
        Assert.notNull(credentials, CREDENTIALS_CANNOT_BEN_NULL);

        if (!(credentials instanceof String))
            return null;

        String password = credentials.toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

//        boolean passwordMatch = userDetails.getPassword().equals(password);
        boolean passwordMatch = passwordEncoder.matches(password, userDetails.getPassword());

        if(!passwordMatch)
            throw new BadCredentialsException(INCORRECT_PASSWORD);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password, userDetails.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
