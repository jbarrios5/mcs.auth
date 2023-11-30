package py.com.jmbr.mcs.auth.util;

import java.time.LocalDateTime;

public class AuthUtil {
    private AuthUtil(){}

    public static LocalDateTime buildAccessTokenCreateDate(){
        return LocalDateTime.now();
    }

    public static LocalDateTime buildAccessTokenInvalidatedDate(){
        return LocalDateTime.now().plusHours(1);
    }
}
