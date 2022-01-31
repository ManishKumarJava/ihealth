package com.life.pharmacy.ihealth.product.service.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO ProductDao;

    public List<ProductDTO> getProducts(){
        return ProductDao.getProducts();
    }
    
}
