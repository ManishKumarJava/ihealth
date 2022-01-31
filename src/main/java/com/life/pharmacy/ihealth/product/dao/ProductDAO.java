package com.life.pharmacy.ihealth.product.dao;

import com.life.pharmacy.ihealth.product.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {

    List<ProductDTO> getProducts();
}
