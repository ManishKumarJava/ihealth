package com.life.pharmacy.ihealth.product.controller;

import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchResultDTO;
import com.life.pharmacy.ihealth.product.exception.ProductException;
import com.life.pharmacy.ihealth.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getProducts() {
        LOG.info("ProductController getProducts API called");
        List<ProductDTO> productDTOs = productService.getProducts();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseBody
    public SearchResultDTO searchProducts(@RequestParam(required = false) String searchWord,
                                           @RequestParam(defaultValue = "0") int pageNo,
                                           @RequestParam(defaultValue = "3") int pageSize,
                                           @RequestParam(defaultValue = "name") String sortByField) {
        LOG.info("ProductController searchProducts API called");
        //Search only on Name field as of now.
        // TODO In Product table we can create a field that is concatenation of all the searchable fields.
        // and search for that field only.
        return productService.searchProducts(searchWord, pageNo, pageSize, sortByField);
    }

    //create a new product
    @PostMapping(value = "/createProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productParam) {
        LOG.info("createProduct API called with Name:{}, ManufacturerName:{}, Description:{}, Price:{} ", productParam.getName(), productParam.getManufacturerName(), productParam.getDescription(), productParam.getPrice());
        ProductDTO productResult = productService.addProduct(productParam);
        if(productResult == null){
            //Just showing that we can throw exception like this.
            throw new ProductException("Exception while creating product record");
        }
        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }





}
