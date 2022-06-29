package br.com.springSecurity.springTutorial.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class ControllerHome {

    @GetMapping
    public String String (){
        return "Hellow World";
    }

}
