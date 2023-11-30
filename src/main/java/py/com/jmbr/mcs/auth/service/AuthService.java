package py.com.jmbr.mcs.auth.service;

import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostResData;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthPostReq;
import py.com.jmbr.java.commons.exception.JMBRException;

public interface AuthService {
    AuthPostResData login(AuthPostReq req) throws JMBRException;
}
