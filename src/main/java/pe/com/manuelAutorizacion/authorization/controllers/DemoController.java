package pe.com.manuelAutorizacion.authorization.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("demo")
    public String demo(){
        return "Hola mundo";

    }

    @GetMapping("/admin")
    public String admin(){
        return "Hello Admin";
    }
    @GetMapping("/dba")
    public  String dba(){
        return "Hello DBA";
    }
    @PostMapping("/add")
    public String add(){
        return "Hello add User";
    }
    @GetMapping("/add")
    public String add2(){
        return "Hello get add User";
    }

}
