package com.likefood.domain.product.service;

import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.ProductType;
import com.likefood.domain.product.model.Type;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Type updateType(Type type);

    Type updateType(TypeDtoU typeDtoU, Type type);

    Type addType(TypeDtoC typeDtoC);

    Type getTypeDetail(Long typeId);

    Type getTypeDetailNotDel(Long typeId);

    /**
     * 新增点心
     * @param productDtoC
     * @return
     */
    ProductDtoV addProduct(ProductDtoC productDtoC, List<Type> typeList);

    /**
     * 修改点心
     * @param productDtoU
     * @param product
     * @return
     */
    ProductDtoV updateProduct(ProductDtoU productDtoU, Product product, List<Type> typeList);

    /**
     * 修改点心信息
     * @param product
     * @return
     */
    Product updateProduct(Product product);

    /**
     * 修改点心库存
     * @param productStockDto
     * @return
     */
    ProductDtoV updateProductStock(ProductStockDto productStockDto, Product product);

    /**
     * 查看点心详情
     * @param productId
     * @return
     */
    ProductDtoV getProductDetail(Long productId);
    Product getProduct(Long productId);

    ProductDtoV getProductDetail(Product product);

    Product getProductDetailNotDel(Long productId);

    /**
     * 查看点心列表
     * @return
     */
    List<Product> getProductList(Long status);
    Page<Product> getProductPage(Long status, Pageable pageable);
    List<Product> getProductListByTypeidOrderBySort(Long typeid);
    Page<Product> getProductPageByTypeid(Long typeid, Pageable pageable);
    List<Product> getWflProduct();
    Page<Product> getWflProductPage(Pageable pageable);

    List<ProductWechatV> getProductWechatVList();

    List<Type> getTypeList(Long status);
    Page<Type> getTypePage(Long status, Pageable pageable);
    Page<Type> getTypePageOrderByUpdatetimeDesc(Long status, Pageable pageable);
    List<TypeDtoV> getTypeLisView();
    List<Type> getTypeListOrderByUpdatetimeDesc(Long status);

    /**
     * 根据修改时间反序查看点心列表
     * @param status
     * @return
     */
    List<Product> getProductListOrderByUpdatetimeDesc(Long status);

    Boolean updateTypeRise(Type type)throws LikefoodException;

    Boolean updateProductRise(ProductType productType)throws LikefoodException;

    Boolean updateProductFall(ProductType productType)throws LikefoodException;

    Boolean updateTypeFall(Type type)throws LikefoodException;


    Boolean exitType(Long id);

    ProductType updateProductType(ProductType productType);
    ProductType getProductType(Long productid, Long typeid);
    List<ProductType> getProductTypeListByTypeId(Long typeid);

    ProductCountDto productCount();
    Long productCountByType(Long typeid);

    Boolean updateProductListByTypeid(ProductTypeDtoU productTypeDtoU);
}
