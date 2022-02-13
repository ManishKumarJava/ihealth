package com.life.pharmacy.ihealth.product.dao.impl;

import com.life.pharmacy.ihealth.product.dao.ProductDAO;
import com.life.pharmacy.ihealth.product.dto.SearchDTO;
import com.life.pharmacy.ihealth.product.repo.ProductRepository;
import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public SearchDTO searchProducts(String searchWord, Pageable pageable){
        Page<ProductEntity> pageResult = productRepository.findByNameContainingIgnoreCase(searchWord, pageable);
        List<ProductDTO> productDtoList = pageResult.get().map(prd -> getProductDTO(prd)).collect(Collectors.toList());
        SearchDTO searchDTO = SearchDTO.builder()
                .productDtoList(productDtoList)
                .searchWord(searchWord)
                .resultTotalElements(pageResult.getTotalElements())
                .resultTotalPages(pageResult.getTotalPages())
                .pageNumber(pageResult.getNumber())
                .pageSize(pageResult.getSize())
                .resultSize(pageResult.getContent().size())
                .build();

        //example to use direct query LIKE.. but case sensitive
        //productRepository.searchByNameLike(searchWord, pageable);

        return searchDTO;
    }

    @Transactional
    public List<ProductDTO> getProducts(){
        List<ProductEntity> productList = productRepository.findAll();
        List<ProductDTO> ProductDtoList = productList.stream().map(prd -> getProductDTO(prd)).collect(Collectors.toList());
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

    //only place holders, can be removed
    private void testPlaceHolder(){
        Sort sort = Sort.by("name").ascending();
        Pageable pageSortedByNameAsc = PageRequest.of(0, 2, sort);
        productRepository.findByNameContainingIgnoreCase("", pageSortedByNameAsc);
        productRepository.findAllByPrice(new BigDecimal(102), pageSortedByNameAsc);
        productRepository.findByName("name-4", pageSortedByNameAsc);
        productRepository.findAllPageByPrice(new BigDecimal(102), pageSortedByNameAsc);
        Page<ProductEntity> page = productRepository.findAll(pageSortedByNameAsc);
        List<ProductEntity> productList = productRepository.findByName("");
        page.getTotalElements();
        page.getTotalPages();
    }

}
