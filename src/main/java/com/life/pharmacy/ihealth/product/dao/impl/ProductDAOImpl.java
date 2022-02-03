package com.life.pharmacy.ihealth.product.dao.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.repo.ProductRepository;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<ProductDTO> getProducts(){
        List<ProductEntity> ProductList = productRepository.findAll();
        List<ProductDTO> ProductDtoList = ProductList.stream().map(prd -> getProductDTO(prd)).collect(Collectors.toList());
        return ProductDtoList;
    }

    @Override
    @Transactional
    public ProductDTO addProduct(ProductDTO dto){
        ProductEntity product = new ProductEntity(dto.getId(), dto.getName(), dto.getManufacturerName(), dto.getDescription(), dto.getPrice());
        ProductEntity productEntityResult = productRepository.save(product);
        return getProductDTO(productEntityResult);
    }

    //returning dto to client (instead of entity)
    private ProductDTO getProductDTO(ProductEntity entity){
        return new ProductDTO(entity.getId(), entity.getName(), entity.getManufacturerName(), entity.getDescription(), entity.getPrice());
    }

}
