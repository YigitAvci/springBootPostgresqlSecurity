package com.hunter.springbootpostgresql.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
