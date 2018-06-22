package com.example.dao.repository;

import com.example.dao.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
    Optional<Product> findByName(String name);
    List<Product> findByManufacturer(String manufacturer);
    boolean existsByName(String name);
}
