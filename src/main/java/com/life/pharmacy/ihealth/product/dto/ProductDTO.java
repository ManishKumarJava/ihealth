package com.life.pharmacy.ihealth.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private int id;

    private String name;

    private String manufacturerName;

    private String description;

    private BigDecimal price;

}
