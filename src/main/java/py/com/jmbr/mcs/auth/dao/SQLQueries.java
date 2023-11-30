package py.com.jmbr.mcs.auth.dao;

public class SQLQueries {
    private SQLQueries(){}
    public static final String ADD_SESSION = "INSERT INTO sessions (user_id,access_token,created,invalidated) values(?,?,?,?)";
}
