package com.hamitmizrak.ui.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    // http://localhost:8080/root
    @GetMapping("/root")
    public String getRoot() {
        return "index";
    }
}
