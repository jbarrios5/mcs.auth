package py.com.jmbr.mcs.auth.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.jmbr.java.commons.domain.mcs.user.User;
import py.com.jmbr.java.commons.domain.mcs.user.UserGetRes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserMapper implements RowMapper<UserGetRes> {
    @Override
    public UserGetRes mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserGetRes userGetRes = new  UserGetRes();
        User user = new User();
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setDocument(rs.getString("document"));
        user.setId(rs.getLong("id"));
        user.setStatus(rs.getString("status"));
        user.setRole(rs.getString("role_name"));
        userGetRes.setUser(user);
        return userGetRes;
    }
}
