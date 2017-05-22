package com.amur.controllers;

import com.amur.constants.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/delivery")
    public String getDeliveryInformationPage(Model model){
        model.addAttribute("title", "Delivery information");
        model.addAttribute("view", "/info/delivery-information");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/privacy")
    public String getPrivacyPage(Model model){
        model.addAttribute("title", "Privacy");
        model.addAttribute("view", "/info/privacy");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/terms-of-use")
    public String getTermsOfUsePage(Model model){
        model.addAttribute("title", "Terms of use");
        model.addAttribute("view", "/info/terms-of-use");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/cookie-policy")
    public String getCookiePolicyPage(Model model){
        model.addAttribute("title", "Cookie policy");
        model.addAttribute("view", "/info/cookies");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/aboutus")
    public String getAboutUsPage(Model model){
        model.addAttribute("title", "About us");
        model.addAttribute("view", "/info/about-us");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/contacts")
    public String getContactsPage(Model model){
        model.addAttribute("title", "Contact us");
        model.addAttribute("view", "/info/contacts");
        return Path.BASE_LAYOUT;
    }
}
