package xyz.aiinirii.postalk.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AIINIRII
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            // if the user do not login yet
            request.setAttribute("checkResult", 3);
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            // else if the user already login
            return true;
        }
    }
}