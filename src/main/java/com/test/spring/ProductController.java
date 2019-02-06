package com.test.spring;

import com.test.beans.Product;
import com.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/{id}")
    public Product findById(@PathVariable Integer id) {
        try {
            return productRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        System.out.println("in update form");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("product", product);
        model.addAttribute("successMsg", null);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, Model model, @Valid Product product, BindingResult result) {
        System.out.println("in update product");

        productRepository.save(product);
        model.addAttribute("successMsg", "Your details saved successfully!");
        return "update-user";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Product product) {
        System.out.println("in sign up form");

        return "add-user";
    }
}
