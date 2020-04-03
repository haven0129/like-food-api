package com.likefood.domain.product.repository;

import com.likefood.domain.product.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByIdAndIdNotNull(@Param("id")Long id);
    Type findFirstById(@Param("id")Long id);

    Type findByIdAndStatus(@Param("id")Long id, @Param("status")Long status);

    List<Type> findByStatusOrderBySort(@Param("status")Long status);

    Page<Type> findByStatusOrderBySort(@Param("status")Long status, Pageable pageable);

    Page<Type> findByStatusOrderByUpdatetimeDesc(@Param("status")Long status, Pageable pageable);

    List<Type> findByStatusOrderByIdDesc(@Param("status")Long status);

    List<Type> findByStatusOrderByUpdatetimeDesc(@Param("status")Long status);

    Type findFirstByStatusAndSortAfterOrderBySort(@Param("status")Long status, @Param("sort")Long sort);

    Type findFirstByStatusAndSortBeforeOrderBySortDesc(@Param("status")Long status, @Param("sort")Long sort);

    @Query(
            value = " select * from t_type where id in(select type_id from t_product_type where product_id = :productid) " +
                    " and status = 0 ORDER BY sort",
            nativeQuery = true
    )
    List<Type> findByProductId(@Param("productid")Long productid);

    Long countByStatus(@Param("status")Long status);


}
