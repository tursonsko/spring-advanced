package pl.strefakursow.springadvanced.component.mailer;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStringFactory {

    private static final String chars = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFHJKLZXCVBNM";

    public static String getRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }
}
