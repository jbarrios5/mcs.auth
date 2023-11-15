package py.com.jmbr.mcs.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.jmbr.mcs.auth.bean.AuthPostRes;
import py.com.jmbr.mcs.auth.bean.AuthPostResData;
import py.com.jmbr.mcs.auth.bean.User;

@RequestMapping("auth/v1")
@RestController
public class AuthDecorator {

    @PostMapping("/login")
    public AuthPostResData login(){
        AuthPostResData data  = new AuthPostResData();
        AuthPostRes res = new AuthPostRes();
        User user = new User();
        user.setId(123l);
        user.setName("JUan");
        res.setUser(user);
        data.setData(res);
        return data;



    }
}
