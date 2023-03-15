package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    private float price;

    private String description;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "categoryId",nullable = false,referencedColumnName = "categoryId")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brandId",nullable = false,referencedColumnName = "brandId")
    @NotNull
    private Brand brand;

}