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
public class SearchResultDTO {
    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private int resultSize;
    private List<ProductDTO> productDtoList;
}
