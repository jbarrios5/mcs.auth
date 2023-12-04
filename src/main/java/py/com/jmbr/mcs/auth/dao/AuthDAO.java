package py.com.jmbr.mcs.auth.dao;

import py.com.jmbr.java.commons.domain.mcs.auth.AuthLogin;
import py.com.jmbr.java.commons.domain.mcs.user.User;

public interface AuthDAO {
    boolean addAuth(User user,String accessToken,String logId);
    boolean isSessionExpires(String accessToken,String logId);
}
