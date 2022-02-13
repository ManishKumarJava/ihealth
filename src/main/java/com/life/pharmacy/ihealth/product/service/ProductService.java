package com.life.pharmacy.ihealth.product.service;

import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts();

    ProductDTO addProduct(ProductDTO productDTO);

    SearchDTO searchProducts(SearchDTO searchDTO);

}
