package py.com.jmbr.mcs.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostReqData;
import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostResData;

import py.com.jmbr.java.commons.exceptions.JMBRException;
import py.com.jmbr.mcs.auth.service.AuthService;


@RequestMapping("auth/v1")
@RestController
public class AuthDecorator {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public AuthPostResData login(@RequestBody AuthPostReqData req) throws JMBRException {
        return authService.login(req.getData());
    }
}