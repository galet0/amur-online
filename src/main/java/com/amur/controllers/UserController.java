package com.amur.controllers;

import com.amur.areas.regions.service.CountryService;
import com.amur.areas.regions.service.RegionService;
import com.amur.areas.users.models.binding.users.RegistrationModel;
import com.amur.areas.users.userService.BasicUserService;
import com.amur.constants.Errors;
import com.amur.constants.Path;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    private final BasicUserService userService;

    private final CountryService countryService;

    private final RegionService regionService;

    @Autowired
    public UserController(BasicUserService userService, CountryService countryService, RegionService regionService) {
        this.userService = userService;
        this.countryService = countryService;
        this.regionService = regionService;
    }

    @ModelAttribute(name = "countryList")
    public List<String> getCountries(){
        List<String> countries = this.countryService.getCountriesNames();
        return countries;
    }

    @ModelAttribute(name = "regionList")
    public List<String> getRegions(){
        List<String> regions = this.regionService.getRegionsNames();
        return regions;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, @ModelAttribute RegistrationModel registrationModel){
        if(!model.containsAttribute("registrationModel")){
            model.addAttribute("registrationModel", new RegistrationModel());
        }
        model.addAttribute("registrationModel", registrationModel);
        model.addAttribute("title", "Register");
        model.addAttribute("view", "/users/register");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult, Model model){
        model.addAttribute("registrationModel", registrationModel);
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Register");
            model.addAttribute("view", "/users/register");
            return Path.BASE_LAYOUT;
        }
//        model.addAttribute("title", "Register");

        this.userService.register(registrationModel);
        model.addAttribute("title", "Home");
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }
        model.addAttribute("title", "Login");
        model.addAttribute("view", "/users/login");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication auth) {

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }
}

