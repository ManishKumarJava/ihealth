package com.life.pharmacy.ihealth.product.service.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchResultDTO;
import com.life.pharmacy.ihealth.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ProductDTO addProduct(ProductDTO productDTO){
        return productDao.addProduct(productDTO);
    }

    @Override
    public SearchResultDTO searchProducts(String searchWord, int pageNoStart, int pageSize, String sortByField){
        //        Sort sort = Sort.by("name").ascending();
//        Pageable pageable = PageRequest.of(0, 2, sort);

        Sort sort = Sort.by(sortByField).ascending();
        Pageable pageable = PageRequest.of(pageNoStart, pageSize, sort);

        return productDao.searchProducts(searchWord, pageable);
    }



    }
