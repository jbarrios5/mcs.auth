package py.com.jmbr.mcs.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.jmbr.java.commons.beans.mcs.user.UserGetResData;
import py.com.jmbr.java.commons.domain.mcs.user.UserGetRes;
import py.com.jmbr.java.commons.logger.RequestUtil;
import py.com.jmbr.mcs.auth.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService{
    private static  final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    @Override
    public UserGetResData getUserByDocument(String document) {
        String logId = RequestUtil.getLogId();
        UserGetResData result = new UserGetResData();
        log.debug(RequestUtil.LOG_FORMATT,logId,"getUserByDocument:Before get user with document=",document);
        UserGetRes data = userDAO.getUserByDocument(document,logId);
        log.debug(RequestUtil.LOG_FORMATT,logId,"getUserByDocument:After get user with ",data.toString());
        result.setData(data);
        return result;
    }
}
