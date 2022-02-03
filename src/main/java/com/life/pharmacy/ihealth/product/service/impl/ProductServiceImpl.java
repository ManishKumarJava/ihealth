package com.life.pharmacy.ihealth.product.service.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDao;

    public List<ProductDTO> getProducts(){
        return productDao.getProducts();
    }

    @Override
    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO){
        return productDao.addProduct(productDTO);
    }

    
}
