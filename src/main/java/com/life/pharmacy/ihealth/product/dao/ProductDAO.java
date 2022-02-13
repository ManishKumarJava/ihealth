package com.life.pharmacy.ihealth.product.dao;

import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDAO {

    List<ProductDTO> getProducts();

    ProductDTO addProduct(ProductDTO productDTO);

    SearchDTO searchProducts(String searchWord, Pageable pageable);


}
