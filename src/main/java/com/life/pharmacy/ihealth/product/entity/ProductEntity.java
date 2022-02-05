package com.life.pharmacy.ihealth.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "PRODUCT")
@Table(name = "PRODUCT")
public class ProductEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String manufacturerName;

    @Column
    private String description;

    @Column
    private BigDecimal price;

}
