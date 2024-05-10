package py.com.jmbr.mcs.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostResData;
import py.com.jmbr.java.commons.beans.mcs.user.UserGetResData;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthLogin;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthPostReq;
import py.com.jmbr.java.commons.domain.mcs.auth.AuthPostRes;
import py.com.jmbr.java.commons.exception.JMBRException;
import py.com.jmbr.java.commons.exception.JMBRExceptionType;
import py.com.jmbr.java.commons.logger.RequestUtil;
import py.com.jmbr.mcs.auth.config.SecurityConfig;
import py.com.jmbr.mcs.auth.dao.AuthDAO;
import py.com.jmbr.mcs.auth.util.AuthUtil;

import java.util.Base64;
import java.util.UUID;

@Service
public class AuthServiceImpl implements  AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthDAO authDAO;
    @Override
    public AuthPostResData login(AuthPostReq req) throws JMBRException {
        String logId = RequestUtil.getLogId();
        AuthPostResData result = new AuthPostResData();
        AuthPostRes data = new AuthPostRes();
        AuthLogin authLogin = new AuthLogin();
        authLogin.setAccessToken(logId);
        // Decodifica la contraseña codificada en Base64 a bytes
        byte[] bytesDecodificados = Base64.getDecoder().decode(req.getPassword());

        // Convierte los bytes decodificados a una cadena
        req.setPassword(new String(bytesDecodificados));

        log.info(RequestUtil.LOG_FORMATT,logId,"login:Starting post login document=",req.getDocument());

        UserGetResData user = userService.getUserByDocument(req.getDocument());

        if(user == null )
            throw new JMBRException("No se pudo obtener el usuario", JMBRExceptionType.WARNING, HttpStatus.BAD_REQUEST);
        log.debug(RequestUtil.LOG_FORMATT,logId,"login:Before check user password ",null);
        boolean isPassCorrect = securityConfig.passwordHashDecode(req.getPassword(),user.getData().getUser().getPassword());
        log.info(RequestUtil.LOG_FORMATT,logId,"login:After check user password ",isPassCorrect);

        if(!isPassCorrect)
            throw new JMBRException("Credenciales invalidas", JMBRExceptionType.FALTAL, HttpStatus.BAD_REQUEST);
        String accessToken = UUID.randomUUID().toString();
        log.debug(RequestUtil.LOG_FORMATT,logId,"login:Before create session user password ",null);
        boolean isSessionCreated = authDAO.addAuth(user.getData().getUser(),accessToken ,logId);
        log.debug(RequestUtil.LOG_FORMATT,logId,"login:After create session user password ",isSessionCreated);
        if(!isSessionCreated)
            throw new JMBRException("Ocurrio un error al crear la session", JMBRExceptionType.FALTAL, HttpStatus.INTERNAL_SERVER_ERROR);
        authLogin.setAccessToken(accessToken);
        authLogin.setCreated(AuthUtil.buildAccessTokenCreateDate().toString());
        authLogin.setExpires(AuthUtil.buildAccessTokenInvalidatedDate().toString());
        //Null pass
        user.getData().getUser().setPassword(null);
        data.setLogin(authLogin);
        data.setUser(user.getData().getUser());
        result.setData(data);
        log.debug(RequestUtil.LOG_FORMATT,logId,"login:Finish create login",data);
        return result;
    }
  
    @Override
    public Boolean isSessionExpires(String accessToken) {
        String logId = RequestUtil.getLogId();
        accessToken = accessToken.split("Bearer")[1].trim();
        log.debug(RequestUtil.LOG_FORMATT,logId,"isSessionExpires:Checking acess_token is expires=",accessToken);
        log.debug(RequestUtil.LOG_FORMATT,logId,"isSessionExpires:Before checking  acess_token= ",accessToken);
        boolean isSessionExpires = authDAO.isSessionExpires(accessToken,logId);
        log.debug(RequestUtil.LOG_FORMATT,logId,"isSessionExpires:After checking  acess_token result= ",isSessionExpires);
        if(isSessionExpires)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }

    
}
