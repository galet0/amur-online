//package com.amur.areas.interseptors;
//
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class TitleInterceptor extends HandlerInterceptorAdapter{
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String title = (String) modelAndView.getModelMap().get("title");
//        String newTitle = "Amur Online - ";
//        if(title != null){
//            newTitle += "" + title;
//        }
//
//        modelAndView.addObject("title", newTitle);
//    }
//}
