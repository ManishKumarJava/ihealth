package com.life.pharmacy.ihealth.product.service.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchDTO;
import com.life.pharmacy.ihealth.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public SearchDTO searchProducts(SearchDTO searchDTO){
        //        Sort sort = Sort.by("name").ascending();
//        Pageable pageable = PageRequest.of(0, 2, sort);

        String searchWord = searchDTO.getSearchWord() != null ? searchDTO.getSearchWord() : "";
        int pageNoStart = searchDTO.getPageNumber();
        int pageSize = searchDTO.getPageSize() > 5  ? searchDTO.getPageSize() : 5; //will make it configurable
        String sortByField = searchDTO.getSortByField() != null ? searchDTO.getSortByField() : "name"; //will make it configurable. and check for empty string

        Sort sort = Sort.by(sortByField).ascending();
        Pageable pageable = PageRequest.of(pageNoStart, pageSize, sort);

        SearchDTO searchResultDTO = productDao.searchProducts(searchWord, pageable);
        searchResultDTO.setSortByField(sortByField);

        return searchResultDTO;
    }



    }
