package com.life.pharmacy.ihealth.product.repo;

import com.life.pharmacy.ihealth.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    List<ProductEntity> findByName(String name);

    Page<ProductEntity> findByNameContainingIgnoreCase(String name , Pageable pageable);

    @Query("SELECT p FROM PRODUCT p WHERE lower(p.name) LIKE lower(concat('%', :name,'%'))")
    Page<ProductEntity> searchByNameLike ( @Param("name") String name , Pageable pageable );

    //----------------------------------------------------------------------------------//
    //    Following are just test method, can be removed
    //----------------------------------------------------------------------------------//

    //Test Method Only for dev
    //TODO check if findAllbyName or findByName... Ans both are same
    List<ProductEntity> findAllByPrice(BigDecimal price, Pageable pageable);
    Page<ProductEntity> findAllPageByPrice(BigDecimal price, Pageable pageable);
    Page<ProductEntity> findByName(String name, Pageable pageable);


    @Query("SELECT p FROM PRODUCT p WHERE p.name LIKE ?1%")
    List<ProductEntity> searchByNameStartsWith(String name);


//    By having it extend PagingAndSortingRepository, we get
//    findAll(Pageable pageable)  for paging
//    findAll(Sort sort)  for sorting.

//    Once we extend PagingAndSortingRepository, we can add our own methods that take Pageable and Sort as parameters,
//    like we did here with findAllByPrice.


}
