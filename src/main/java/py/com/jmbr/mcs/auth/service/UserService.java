package py.com.jmbr.mcs.auth.service;

import py.com.jmbr.java.commons.beans.mcs.user.UserGetResData;

public interface UserService {
    UserGetResData getUserByDocument(String document);
}
