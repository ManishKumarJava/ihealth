package com.life.pharmacy.ihealth.product.service;

import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchResultDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts();

    ProductDTO addProduct(ProductDTO productDTO);

    SearchResultDTO searchProducts(String searchWord, int pageNoStart, int pageSize, String sortByField);

}
