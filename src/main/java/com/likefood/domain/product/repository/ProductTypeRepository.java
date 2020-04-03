package com.likefood.domain.product.repository;

import com.likefood.domain.product.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    /**
     * 查询此类型的点心最后一个排序是多少
     * @param typeid
     * @return
     */
    ProductType findFirstByTypeIdOrderBySortDesc(@Param("typeid")Long typeid);

    List<ProductType> findByTypeId(@Param("typeid")Long typeid);

    List<ProductType> findByProductId(@Param("productid")Long productid);

    List<ProductType> findByProductIdAndTypeId(@Param("productid")Long productid, @Param("typeid")Long typeid);

    ProductType findFirstByProductIdInAndTypeIdAndSortAfterOrderBySort(@Param("productids")List<Long> productids, @Param("typeid")Long typeid, @Param("sort")Long sort);

    ProductType findFirstByProductIdInAndTypeIdAndSortBeforeOrderBySortDesc(@Param("productids")List<Long> productids, @Param("typeid")Long typeid, @Param("sort")Long sort);




}
