package py.com.jmbr.mcs.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
    public   boolean passwordHashDecode(String passwordPlain,String passwordHash){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(passwordPlain,passwordHash);

    }

}
