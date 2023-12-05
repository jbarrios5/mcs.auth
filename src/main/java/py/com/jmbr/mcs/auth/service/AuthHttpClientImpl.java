package py.com.jmbr.mcs.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import py.com.jmbr.java.commons.beans.mcs.user.UserGetResData;
import py.com.jmbr.java.commons.exception.JMBRException;
import py.com.jmbr.java.commons.exception.JMBRExceptionType;
import py.com.jmbr.java.commons.logger.RequestUtil;

@Component
public class AuthHttpClientImpl implements AuthHttpClient{
    private String USER_URI = "http://localhost:8082/mcs.user/user/v1";
    private static final Logger logger = LoggerFactory.getLogger(AuthHttpClientImpl.class);
    @Autowired
    private RestTemplate authRestTemplate;
    @Override
    public UserGetResData getUserByDocument(String document,String logId) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<UserGetResData> response = null;
        HttpEntity<?> entity = new HttpEntity<>(HttpEntity.EMPTY,headers);
        String uri = UriComponentsBuilder.fromUriString(USER_URI.concat("/"))
                        .queryParam("document",document).toUriString();
        try {
            logger.info(RequestUtil.LOG_FORMATT,logId,"getUserByDocument:request",entity);
            response = authRestTemplate.exchange(uri, HttpMethod.GET,entity,UserGetResData.class);
            logger.info(RequestUtil.LOG_FORMATT,logId,"getUserByDocument:response",response.getBody());
            if(response.getBody() != null)
                return response.getBody();
            else
                return null;
        }catch (JMBRException e){
            logger.warn(RequestUtil.LOG_FORMATT,logId,"getUserByDocument:Unexpected error getting user",e.getMessage());
            throw new JMBRException("Ocurrio un error al obtener el usuario", JMBRExceptionType.WARNING, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
