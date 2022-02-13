package com.life.pharmacy.ihealth.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SearchDTO {

    private String searchWord;
    private int pageNumber;
    private int pageSize;
    private String sortByField;

    private long resultTotalElements;
    private int resultTotalPages;
    private int resultSize;//this should be same as pageSize TODO

    private List<ProductDTO> productDtoList;
}
