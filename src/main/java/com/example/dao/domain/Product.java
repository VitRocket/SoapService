package com.example.dao.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
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
    @Column(length = 20)
    private String name;
    @NonNull
    @Column(length = 100)
    private String description;
    @NonNull
    private String manufacturer;


}
