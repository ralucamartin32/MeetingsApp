package edu.arobs.meetingsapp.configuration;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestInterceptor implements HandlerInterceptor {

    private ThreadLocal myThreadLocal = new ThreadLocal();
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
                             Object handler) throws Exception {
        System.out.println("Called before handler method");
        Cookie[] requestCookies = req.getCookies();
        for(Cookie c : requestCookies){
            if(c.getName().equals("token")) {
                myThreadLocal.set(c.getValue());
                System.out.println(myThreadLocal.get());
            }
        }

        return true;
    }

    // Called after handler method request completion, before rendering the view
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res,
                           Object handler, @Nullable ModelAndView modelAndView)  throws Exception {
        System.out.println("Called after handler method request completion,"
                + " before rendering the view");

        myThreadLocal.set(null);
        System.out.println("POST method " + myThreadLocal.get());
    }

    // Called after rendering the view
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res,
                                Object handler, Exception ex)  throws Exception {
        System.out.println("Called after rendering the view");
    }





}
