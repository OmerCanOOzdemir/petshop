package com.example.petshop.controllers;


import com.example.petshop.dao.CategoryRepository;
import com.example.petshop.dao.ProductRepository;
import com.example.petshop.entities.Product;
import com.example.petshop.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @ModelAttribute("product")
    public Product product(){
        return new Product();
    }
    @ModelAttribute("all_categories")
    public Iterable<Category> categories(){
        return categoryRepository.findAll();
    }
    @GetMapping(value = {"/"})
    public String index(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "product/index";
    }

    @GetMapping(value = {"/create"})
    public String create(){
        return "product/create";
    }
    @PostMapping(value = {"/create"})
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam(name = "file") MultipartFile file){
        if (bindingResult.hasErrors()){
            return "product/create";
        }
        else {
            try {
                product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                productRepository.save(product);
                redirectAttributes.addFlashAttribute("success","Product created");
                return "redirect:/";
            } catch (IOException e) {
                return "redirect:/create?error";
            }

        }
    }
    @GetMapping(value = {"/delete/{id}"})
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes){
        productRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success","Product deleted");
        return "redirect:/";
    }
    @GetMapping(value = {"/edit/{id}"})
    public String editPage(@PathVariable(value = "id")int id, Model model){
        Product product = productRepository.findById(id).get();
        model.addAttribute("old_product",product);
        return "product/edit";
    }



}
