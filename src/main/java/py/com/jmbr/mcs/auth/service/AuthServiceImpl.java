package py.com.jmbr.mcs.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostResData;
import py.com.jmbr.java.commons.domain.User;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthLogin;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthPostReq;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthPostRes;
import py.com.jmbr.java.commons.exceptions.JMBRException;
import py.com.jmbr.java.commons.exceptions.JMBRExceptionType;
import py.com.jmbr.java.commons.logger.RequestUtil;

@Service
public class AuthServiceImpl implements  AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Override
    public AuthPostResData login(AuthPostReq req) throws JMBRException {
        String logId = RequestUtil.getLogId();
        AuthPostResData result = new AuthPostResData();
        AuthPostRes data = new AuthPostRes();
        AuthLogin authLogin = new AuthLogin();
        authLogin.setAccessToken(logId);
        log.info(RequestUtil.LOG_FORMATT,logId,"login:Starting post login document=",req.getDocument());
        if( !req.getDocument().equals("12") || !req.getPassword().equals("11")){
            log.warn(RequestUtil.LOG_FORMATT,logId,"login:Error validating credentilas",null);
            throw new JMBRException("Credenciales invalidas", JMBRExceptionType.FALTAL, HttpStatus.BAD_REQUEST);
        }
        log.info(RequestUtil.LOG_FORMATT,logId,"login:Finish post login document=",req.getDocument());
        data.setLogin(authLogin);
        data.setUser(new User());
        result.setData(data);
        return result;
    }
}
