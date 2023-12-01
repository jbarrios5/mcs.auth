package py.com.jmbr.mcs.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostReqData;
import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostResData;

import py.com.jmbr.java.commons.exception.JMBRException;
import py.com.jmbr.mcs.auth.service.AuthService;

import javax.validation.Valid;


@RequestMapping("auth/v1")
@RestController
public class AuthDecorator {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    @Operation(summary = "authenticate user",description = "Authenticate user by password and document")
    public AuthPostResData login(@RequestBody @Valid AuthPostReqData req) throws JMBRException {
        return authService.login(req.getData());
    }

    @PostMapping("/verify")
    @Operation(summary = "authenticate user",description = "Authenticate user by password and document")
    public Boolean verifyAccessToken(@RequestParam(value = "access_token")String accessToken) throws JMBRException {
        return Boolean.TRUE;
    }
}
