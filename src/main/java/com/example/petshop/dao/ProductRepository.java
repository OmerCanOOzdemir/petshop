package com.example.petshop.dao;

import com.example.petshop.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository  extends CrudRepository<Product,Integer> {

}
