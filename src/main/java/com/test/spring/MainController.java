package com.test.spring;

import com.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @Value("${spring.application.name}")
    String appName;


    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/showproducts")
    public String findAll(Model model) {

        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

}
