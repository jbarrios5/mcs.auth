package py.com.jmbr.mcs.auth.service;

import py.com.jmbr.java.commons.beans.mcs.user.UserGetResData;

public interface AuthHttpClient {
    UserGetResData getUserByDocument(String document,String logId);
}
