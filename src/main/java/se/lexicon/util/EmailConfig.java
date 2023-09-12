package se.lexicon.util;

import se.lexicon.exception.EmailConfigException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailConfig {

    private String email;
    private String password;


    public EmailConfig() {

        Properties properties = new Properties();
        try (
                InputStream input = EmailConfig.class.getClassLoader().getResourceAsStream("config.properties");
        ) {
            if (input != null) {
                properties.load(input);
                this.email = properties.getProperty("email");
                this.password = properties.getProperty("password");
            }

        } catch (IOException e) {
           throw new EmailConfigException("IOException: " + e);
        }

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
