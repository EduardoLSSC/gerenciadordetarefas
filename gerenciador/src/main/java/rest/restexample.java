package rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class restexample {

    @GetMapping("/get")
    public String hello() {
        System.out.printf("dsadasdas");
        return "Hello World!";
    }
}
