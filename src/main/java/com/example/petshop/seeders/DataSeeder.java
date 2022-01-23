package com.example.petshop.seeders;


import com.example.petshop.dao.CategoryRepository;
import com.example.petshop.dao.RoleRepository;
import com.example.petshop.entities.Category;
import com.example.petshop.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        insertCategories();
        insertRoles();
    }
    private void insertCategories() {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                    new Category("Voeding"), new Category("Speelgoed"),new Category("Habitat")
            ));
        }
    }
    private void insertRoles(){
        if(roleRepository.count() == 0){
            roleRepository.saveAll(
                    List.of(new Role("admin"),new Role("user"))
            );
        }
    }

}
