package com.example.dao.service;

import com.example.dao.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product getProductByName(String name);
    Product addProduct(Product product) throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(Product product);
}
