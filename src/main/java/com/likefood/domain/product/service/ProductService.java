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
    /**
     * 修改产品类型
     * @param type
     * @return
     */
    Type updateType(Type type);

    /**
     * 修改产品类型
     * @param typeDtoU
     * @param type
     * @return
     */
    Type updateType(TypeDtoU typeDtoU, Type type);

    /**
     * 新增产品类型
     * @param typeDtoC
     * @return
     */
    Type addType(TypeDtoC typeDtoC);

    /**
     * 根据类型ID查询产品类型详情
     * @param typeId
     * @return
     */
    Type getTypeDetail(Long typeId);

    /**
     * 查询未删除的产品类型详情
     * @param typeId
     * @return
     */
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

    /**
     * 根据产品ID查询产品信息
     * @param productId
     * @return
     */
    Product getProduct(Long productId);

    /**
     * 根据产品信息构建产品详情dto
     * @param product
     * @return
     */
    ProductDtoV getProductDetail(Product product);

    /**
     * 根据产品ID查询未删除的产品详情
     * @param productId
     * @return
     */
    Product getProductDetailNotDel(Long productId);

    /**
     * 查看点心列表
     * @return
     */
    List<Product> getProductList(Long status);

    /**
     * 根据状态查询产品（分页）
     * @param status
     * @param pageable
     * @return
     */
    Page<Product> getProductPage(Long status, Pageable pageable);

    /**
     * 根据类型查询产品列表（排序）
     * @param typeid
     * @return
     */
    List<Product> getProductListByTypeidOrderBySort(Long typeid);

    /**
     * 根据类型查询产品（分页）
     * @param typeid
     * @param pageable
     * @return
     */
    Page<Product> getProductPageByTypeid(Long typeid, Pageable pageable);

    /**
     * 查询产品列表（未分类）
     * @return
     */
    List<Product> getWflProduct();

    /**
     * 查询产品信息（分页）（未分类）
     * @param pageable
     * @return
     */
    Page<Product> getWflProductPage(Pageable pageable);

    /**
     * 微信首页展示的产品列表
     * @return
     */
    List<ProductWechatV> getProductWechatVList();

    /**
     * 根据状态查询类型列表
     * @param status
     * @return
     */
    List<Type> getTypeList(Long status);

    /**
     * 根据状态查询类型（分页）
     * @param status
     * @param pageable
     * @return
     */
    Page<Type> getTypePage(Long status, Pageable pageable);

    /**
     * 根据状态查询类型并根据修改时间倒序（分页）
     * @param status
     * @param pageable
     * @return
     */
    Page<Type> getTypePageOrderByUpdatetimeDesc(Long status, Pageable pageable);

    /**
     * 返回类型dto的列表
     * @return
     */
    List<TypeDtoV> getTypeLisView();

    /**
     * 根据状态查询类型列表，并根据修改时间倒序
     * @param status
     * @return
     */
    List<Type> getTypeListOrderByUpdatetimeDesc(Long status);

    /**
     * 根据修改时间反序查看点心列表
     * @param status
     * @return
     */
    List<Product> getProductListOrderByUpdatetimeDesc(Long status);

    /**
     * 类型排序前进1
     * @param type
     * @return
     * @throws LikefoodException
     */
    Boolean updateTypeRise(Type type)throws LikefoodException;

    /**
     * 产品排序前进1
     * @param productType
     * @return
     * @throws LikefoodException
     */
    Boolean updateProductRise(ProductType productType)throws LikefoodException;

    /**
     * 产品排序后退1
     * @param productType
     * @return
     * @throws LikefoodException
     */
    Boolean updateProductFall(ProductType productType)throws LikefoodException;

    /**
     * 类型排序后退1
     * @param type
     * @return
     * @throws LikefoodException
     */
    Boolean updateTypeFall(Type type)throws LikefoodException;

    /**
     * 根据类型ID判断此类型是否存在
     * @param id
     * @return
     */
    Boolean exitType(Long id);

    /**
     * 修改产品类型关联
     * @param productType
     * @return
     */
    ProductType updateProductType(ProductType productType);

    /**
     * 根据类型ID和产品ID查询两者关联信息
     * @param productid
     * @param typeid
     * @return
     */
    ProductType getProductType(Long productid, Long typeid);

    /**
     * 根据类型ID查询产品列表
     * @param typeid
     * @return
     */
    List<ProductType> getProductTypeListByTypeId(Long typeid);

    /**
     * 统计产品数量
     * @return
     */
    ProductCountDto productCount();

    /**
     * 根据类型ID统计产品质量
     * @param typeid
     * @return
     */
    Long productCountByType(Long typeid);

    /**
     * 根据类型ID批量修改此类型下的产品
     * @param productTypeDtoU
     * @return
     */
    Boolean updateProductListByTypeid(ProductTypeDtoU productTypeDtoU);
}
