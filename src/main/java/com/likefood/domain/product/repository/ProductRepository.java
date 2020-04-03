package com.likefood.domain.product.repository;

import com.likefood.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdAndIdNotNull(@Param("id")Long id);

    Product findByIdAndStatus(@Param("id")Long id, @Param("status")Long status);

    List<Product> findByStatusOrderByIdDesc(@Param("status")Long status);

    List<Product> findByStatusOrderByUpdatetimeDesc(@Param("status")Long status);

    /**
     * 获得正常状态的产品
     * @return
     */
    @Query(
//            value = "select * from t_product where id in(select product_id from t_product_type where type_id = :typeid ORDER BY sort) and status = 0",
            value = " select p.*, pt.sort from t_product p " +
                    "   left join t_product_type pt on p.id = pt.product_id " +
                    " where pt.type_id = :typeid and p.status = 0 " +
                    " order by sort",
            nativeQuery = true
    )
    List<Product> findByTypeidOrderBySort(@Param("typeid")Long typeid);

    @Query(
            value = " select p.*, pt.sort from t_product p " +
                    "   left join t_product_type pt on p.id = pt.product_id " +
                    " where pt.type_id = :typeid and p.status = 0 " +
                    " order by sort" +
                    "  \n-- #pageable\n",
            countQuery =  " select count(1) from t_product p " +
                    "   left join t_product_type pt on p.id = pt.product_id " +
                    " where pt.type_id = :typeid and p.status = 0 ",
            nativeQuery = true
    )
    Page<Product> findByTypeidPage(@Param("typeid")Long typeid, Pageable pageable);
    /**
     * 获得未分类正常状态的产品
     * @return
     */
    @Query(
            value = "select * from t_product where id not in ( " +
                    "       select DISTINCT product_id from t_product_type where type_id in(select id from t_type where status = 0) " +
                    ") and status = 0 order by createtime desc",
            nativeQuery = true
    )
    List<Product> findWflProduct();

    @Query(
            value = "select * from t_product where id not in ( " +
                    "       select DISTINCT product_id from t_product_type where type_id in(select id from t_type where status = 0) " +
                    ") and status = 0 order by createtime desc" +
                    " \n-- #pageable\n",
            countQuery = "select count(1) from t_product where id not in ( " +
            "       select DISTINCT product_id from t_product_type where type_id in(select id from t_type where status = 0) " +
            ") and status = 0 ",
            nativeQuery = true
    )
    Page<Product> findWflProduct(Pageable pageable);

    Page<Product> findByStatus(@Param("status")Long status, Pageable pageable);

    Long countByStatus(@Param("status")Long status);

    @Query(
            value = "select count(*) from t_product where id not in ( " +
                    "       select DISTINCT product_id from t_product_type where type_id in(select id from t_type where status = 0) " +
                    ") and status = 0 order by createtime desc",
            nativeQuery = true
    )
    Long countWflProduct();

    @Query(
            value = " select count(*) from t_product p " +
                    "   left join t_product_type pt on p.id = pt.product_id " +
                    " where pt.type_id = :typeid and p.status = 0 ",
            nativeQuery = true
    )
     Long countByTypeid(@Param("typeid")Long typeid);
}
