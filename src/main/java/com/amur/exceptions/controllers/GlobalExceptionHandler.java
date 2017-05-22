//package com.amur.exceptions.controllers;
//
//import com.amur.constants.Path;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public String handleGlobalExceptions(Model model){
//        model.addAttribute("view", "error/custom-error");
//        return Path.BASE_LAYOUT;
//    }
//
//}
