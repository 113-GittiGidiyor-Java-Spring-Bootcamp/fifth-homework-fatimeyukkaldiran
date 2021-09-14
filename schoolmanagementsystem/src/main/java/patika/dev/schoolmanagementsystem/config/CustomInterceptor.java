package patika.dev.schoolmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import patika.dev.schoolmanagementsystem.utils.ClientRequestInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ClientRequestInfo clientRequestInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      clientRequestInfo.setClientURL(request.getRequestURI());
      clientRequestInfo.setClientIPAddress(request.getRemoteAddr());
      clientRequestInfo.setSessionActivityID(request.getSession().getId());
        return true;
    }
}
