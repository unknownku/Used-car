package com.oot.usedcar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oot.usedcar.domain.User;
import com.oot.usedcar.form.UserLoginForm;
import com.oot.usedcar.service.SecurityService;
import com.oot.usedcar.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
    	user.setUsername(userLoginForm.getUsername());
    	user.setPassword(userLoginForm.getPassword());
        userService.save(user);
        securityService.autologin(userLoginForm.getUsername(), userLoginForm.getPassword());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
            return "login";
        }
    	
    	securityService.autologin(userLoginForm.getUsername(), userLoginForm.getPassword());
        return "redirect:/welcome";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
    	model.addAttribute("userLoginForm", new UserLoginForm());
        return "login";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
