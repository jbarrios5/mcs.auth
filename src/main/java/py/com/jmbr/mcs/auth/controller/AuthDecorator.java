package py.com.jmbr.mcs.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostReqData;
import py.com.jmbr.java.commons.beans.mcs.auth.AuthPostResData;

import py.com.jmbr.java.commons.beans.mcs.user.UserPostReqData;
import py.com.jmbr.java.commons.beans.mcs.user.UserPostResData;
import py.com.jmbr.java.commons.context.OperationAllow;
import py.com.jmbr.java.commons.exception.JMBRException;
import py.com.jmbr.mcs.auth.annotations.AuthSecurityAccess;
import py.com.jmbr.mcs.auth.service.AuthService;

import javax.validation.Valid;


@RequestMapping("auth/v1")
@RestController
public class AuthDecorator {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    @AuthSecurityAccess(operation = OperationAllow.POST_LOGIN)
    @Operation(summary = "authenticate user",description = "Authenticate user by password and document")
    public AuthPostResData login(
            @RequestHeader(value = "apiKey",required = true)String apiKey,
            @RequestBody @Valid AuthPostReqData req) throws JMBRException {
        return authService.login(req.getData());
    }

    @PostMapping("/verify")
    @AuthSecurityAccess(operation = OperationAllow.VERIFY_TOKEN)
    @Operation(summary = "verify accessToken",description = "Verify accessToken is expires")
    public Boolean verifyAccessToken(
            @RequestHeader(value = "apiKey",required = true)String apiKey,
            @RequestHeader(value = "Authorization")String accessToken) throws JMBRException {
        return authService.isSessionExpires(accessToken);
    }

}
