package py.com.jmbr.mcs.auth.dao;

import py.com.jmbr.java.commons.domain.mcs.user.User;

public interface AuthDAO {
    boolean addAuth(User user,String accessToken,String logId);
    AuthLogin getSession(String accessToken);
}
