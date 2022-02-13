package com.life.pharmacy.ihealth.product.controller;

import com.life.pharmacy.ihealth.product.dto.ProductDTO;
import com.life.pharmacy.ihealth.product.dto.SearchDTO;
import com.life.pharmacy.ihealth.product.exception.ProductException;
import com.life.pharmacy.ihealth.product.service.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

//    @GetMapping("/searchGet")
//    @ResponseBody
//    public SearchDTO searchProductsGet(@RequestParam(required = false) String searchWord,
//                                       @RequestParam(defaultValue = "0") int pageNo,
//                                       @RequestParam(defaultValue = "3") int pageSize,
//                                       @RequestParam(defaultValue = "name") String sortByField) {
//        LOG.info("ProductController searchProducts API called");
//        //Search only on Name field as of now.
//        // TODO In Product table we can create a field that is concatenation of all the searchable fields.
//        // and search for that field only.
//        return productService.searchProducts(searchWord, pageNo, pageSize, sortByField);
//    }

    @PostMapping("/search")
    @ResponseBody
    public SearchDTO searchProducts(@RequestBody SearchDTO searchDTO) {
        LOG.info("ProductController searchProducts POST API called");
        //Search only on Name field as of now.
        // TODO In Product table we can create a field that is concatenation of all the searchable fields.
        // and search for that field only.
        return productService.searchProducts(searchDTO);
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


    //create a new product
    @PostMapping(value = "/createTestProducts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> createTestProducts(@RequestBody ProductDTO productParam) {
        LOG.info("createProduct API called with Name:{}, ManufacturerName:{}, Description:{}, Price:{} ", productParam.getName(), productParam.getManufacturerName(), productParam.getDescription(), productParam.getPrice());
        ProductDTO productResult = productService.addProduct(productParam);

        for(int i = 0; i<1000; i++) {
            ProductDTO product = new ProductDTO(
                    productParam.getId() + generateRandomNo(),
                    productParam.getName() + generateRandomStr(),
                    productParam.getManufacturerName() + generateRandomStr(),
                    productParam.getDescription() + generateRandomStr(),
                    productParam.getPrice().add( generateRandomDouble() )
            );
            ProductDTO productResultTemp = productService.addProduct(product);
        }

        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }
    public int generateRandomNo() {
        int min = 100;
        int max = 1000;
        int a = (int)(Math.random()*(max-min+1)+min);
        return a;
    }
    public BigDecimal generateRandomDouble() {
        int min = 100;
        int max = 1000;
        double a = Math.random()*(max-min+1)+min;
        return new BigDecimal(a);
    }
    public String generateRandomStr() {
        int length = 3;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println(generatedString);
        return "_" +generatedString;
    }


}
