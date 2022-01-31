package com.life.pharmacy.ihealth.product.dao.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.dao.repo.ProductRepository;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository ProductRepository;

    @Transactional
    public List<ProductDTO> getProducts(){
        List<ProductEntity> ProductList = ProductRepository.findAll();
        List<ProductDTO> ProductDtoList = ProductList.stream().map(l -> new ProductDTO(l.getName())).collect(Collectors.toList());
        return ProductDtoList;
    }

}
