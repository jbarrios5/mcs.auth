package py.com.jmbr.mcs.auth.dao;

import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import py.com.jmbr.java.commons.domain.mcs.user.UserGetRes;
import py.com.jmbr.java.commons.exception.JMBRException;
import py.com.jmbr.java.commons.exception.JMBRExceptionType;
import py.com.jmbr.java.commons.logger.RequestUtil;
import py.com.jmbr.mcs.auth.mapper.GetUserMapper;

@Repository
public class UserDAOImpl implements UserDAO{
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    @Autowired
    private JdbcTemplate jdbcPGS;
    @Override
    public UserGetRes getUserByDocument(String document, String logId) {
        try {
            return jdbcPGS.queryForObject(SQLQueries.GET_USER, new Object[]{document} , new GetUserMapper());
        }catch (DataAccessException e){
            logger.warn(RequestUtil.LOG_FORMATT,logId,"getUserByDocument: Unexpected error getting user",e.getMessage());
            throw new JMBRException("Ocurrio un error inesperado", JMBRExceptionType.FALTAL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
