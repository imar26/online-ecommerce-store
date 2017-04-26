package com.neu.project.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {
	String errorPage;

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("----------------------");
        HttpSession session = (HttpSession) request.getSession();
        System.out.println("--"+session.getAttribute("role"));
        
        if(request.getParameter("buyersignup")!=null){
            if(request.getParameter("buyersignup").equals("Register Buyer")){
                session.setAttribute("role", "buyer");
            }   
        }
        if(request.getParameter("sellersignup")!=null){
            if(request.getParameter("sellersignup").equals("Register Seller")){
                session.setAttribute("role", "seller");
            } 
        }
        
        
        if(session.getAttribute("role") == null){
            if((request.getRequestURI().contains("seller/"))||
                    (request.getRequestURI().contains("buyer/"))||(request.getRequestURI().contains("admin/")))
            {
                System.out.println("in interceptor1");
                System.out.println("1 -false");
                response.sendRedirect(request.getContextPath());
                return false;
            }
            System.out.println("in interceptor2");
            return true;
        }

        if(session.getAttribute("role") != null){
            System.out.println("in interceptor3");
            if((request.getRequestURI().contains("admin") && session.getAttribute("role").equals("admin")) ||
                    (request.getRequestURI().contains("seller") && session.getAttribute("role").equals("seller"))||
                    (request.getRequestURI().contains("buyer") && session.getAttribute("role").equals("buyer")))
            {
                System.out.println("in interceptor4");
                return true;
            }else if((!request.getRequestURI().contains("admin"))&&
                    (!request.getRequestURI().contains("seller"))&&
                    (!request.getRequestURI().contains("buyer")))
            {
                System.out.println("in interceptor5");
                return true;
            }
        }

        System.out.println("NOT VALID!!");
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath());
        System.out.println("1 -false");
        return false;
    }
}
