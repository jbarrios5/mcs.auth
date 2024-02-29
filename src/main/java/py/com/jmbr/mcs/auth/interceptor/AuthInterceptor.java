package py.com.jmbr.mcs.auth.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import py.com.jmbr.java.commons.context.OperationAllow;
import py.com.jmbr.java.commons.context.SecurityAccess;
import py.com.jmbr.java.commons.exception.JMBRException;
import py.com.jmbr.java.commons.exception.JMBRExceptionType;
import py.com.jmbr.mcs.auth.annotations.AuthSecurityAccess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class AuthInterceptor  implements HandlerInterceptor {
    @Value("${api.key}")
    private String apiKey;
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Permitir que las solicitudes preflight CORS pasen
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        if (request.getRequestURI().contains("/swagger") || request.getRequestURI().contains("/v3/api-docs") || request.getRequestURI().contains("/swagger-ui/")) {
            return true;
        }

        logger.info("Request URI",request.getRequestURI().toString());
        String apiKeyHeader = request.getHeader("apiKey");

        if(apiKeyHeader == null )
            throw new JMBRException("ApiKey is required", JMBRExceptionType.FALTAL, HttpStatus.BAD_REQUEST);

        HandlerMethod method = (HandlerMethod) handler;
        AuthSecurityAccess securityAccess = AnnotationUtils.findAnnotation(method.getMethod(), AuthSecurityAccess.class);

        if(securityAccess.operation() == OperationAllow.POST_LOGIN){
            if(!apiKeyHeader.equals(apiKey))
                throw new JMBRException("ApiKey invalid", JMBRExceptionType.FALTAL, HttpStatus.BAD_REQUEST);
            else
                return Boolean.TRUE;
        }
        if(!apiKeyHeader.equals(apiKey))
            throw new JMBRException("ApiKey invalid", JMBRExceptionType.FALTAL, HttpStatus.BAD_REQUEST);

        if(request.getHeader("Authorization") == null)
            throw new JMBRException("AT is required", JMBRExceptionType.FALTAL, HttpStatus.BAD_REQUEST);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
