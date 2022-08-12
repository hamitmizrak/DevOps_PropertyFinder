package com.hamitmizrak.bean.login;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
public class UserController {

    //global variable
    private IUserService userService;

    //paramatreli constructor
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //http://localhost:8080/login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "index";
    }

    //http://localhost:8080/user/register
    @GetMapping("/user/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "register";
    }

    //http://localhost:8080/user/register
    @PostMapping("/user/register")
    public String postRegisterForm(@ModelAttribute("user") UserRegisterDto  userRegisterDto  ) {
        //database kayıt olacak
        log.info(userRegisterDto);
        userService.save(userRegisterDto);
        return  "redirect:/user/register?success";
    }

}
