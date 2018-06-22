package com.example.dao.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String manufacturer;


}
