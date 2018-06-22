package com.example.dao.service;

import com.example.dao.domain.Product;
import com.example.dao.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException("DB not contains Product on id: " + id);
        }
    }

    @Override
    public Product getProductByName(String name) {
        Optional<Product> optional = productRepository.findByName(name);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException("DB not contains Product on id: " + name);
        }
    }

    @Override
    public Product addProduct(Product product) throws SQLException {
        if (productRepository.existsByName(product.getName())) {
            throw new SQLException("DB contains product: " + product.getName());
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
