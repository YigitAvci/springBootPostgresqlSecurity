package com.hunter.springbootpostgresql.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Data // if you use lombok, you don't need to create constructors, getters, setters and many more.
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    // it is not necessary any more, because we used @JoinColumn annotation for this
    //@Column(name = "category_id")
    //private int categoryId;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "units_in_stock")
    private Short unitsInStock;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;
}
