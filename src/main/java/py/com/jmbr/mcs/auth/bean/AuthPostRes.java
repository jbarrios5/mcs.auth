package py.com.jmbr.mcs.auth.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthPostRes {
    private User user;
    private AuthLogin login;


}
