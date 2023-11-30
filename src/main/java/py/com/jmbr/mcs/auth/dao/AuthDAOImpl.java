package py.com.jmbr.mcs.auth.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import py.com.jmbr.java.commons.domain.mcs.user.User;
import py.com.jmbr.java.commons.logger.RequestUtil;
import py.com.jmbr.mcs.auth.util.AuthUtil;

@Repository
public class AuthDAOImpl implements AuthDAO{
    private static final Logger logger = LoggerFactory.getLogger(AuthDAOImpl.class);
    @Autowired
    private JdbcTemplate jdbcPGS;
    @Override
    public boolean addAuth(User user, String accessToken, String logId) {
        int result = 0;
        try {
            result = jdbcPGS.update(SQLQueries.ADD_SESSION,
                    user.getId(),accessToken, AuthUtil.buildAccessTokenCreateDate(),AuthUtil.buildAccessTokenInvalidatedDate());
        }catch (DataAccessException e){
            logger.warn(RequestUtil.LOG_FORMATT,logId,"addAuth:Unexpected error inserting session",e.getMessage());
            result = 0;
        }
        return (result > 0);
    }
}
