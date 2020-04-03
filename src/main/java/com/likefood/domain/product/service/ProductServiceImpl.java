package com.likefood.domain.product.service;

import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.ProductType;
import com.likefood.domain.product.model.Type;
import com.likefood.domain.product.repository.ProductRepository;
import com.likefood.domain.product.repository.ProductTypeRepository;
import com.likefood.domain.product.repository.TypeRepository;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.common.MsgValue;
import com.likefood.pojo.product.*;
import com.likefood.pojoconverter.ProductDtoConverter;
import com.likefood.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @Override
    public Boolean updateProductListByTypeid(ProductTypeDtoU productTypeDtoU) {
        List<Long> productIdList = productTypeDtoU.getProductIdList();
        List<ProductType> productTypeList = getProductTypeListByTypeId(productTypeDtoU.getTypeId());
        if(ListUtils.isNotNull(productTypeList)){
            productTypeRepository.deleteAll(productTypeList);
        }
        if(ListUtils.isNotNull(productIdList)){
            Long sort = ConstantValue.T_PRODUCT_TYPE_SORT_INIT;
            for(Long productId : productIdList){
                ProductType productType = new ProductType();
                productType.setTypeId(productTypeDtoU.getTypeId());
                productType.setProductId(productId);
                productType.setSort(sort);
                updateProductType(productType);
                sort++;
            }
        }
        return true;
    }

    @Override
    public Long productCountByType(Long typeid) {
        if(typeid.equals(ConstantValue.T_TYPE_ID_WFL)){
            return productRepository.countWflProduct();
        }else{
            return productRepository.countByTypeid(typeid);
        }
    }

    @Override
    public ProductCountDto productCount() {
        Long typeCount = typeRepository.countByStatus(ConstantValue.T_TYPE_STATUS_ZC);
        Long productCount = productRepository.countByStatus(ConstantValue.T_PRODUCT_STATUS_ZC);
        Long lowerProductCount = productRepository.countByStatus(ConstantValue.T_PRODUCT_STATUS_XJ);
        ProductCountDto result = new ProductCountDto();
        result.setTypeCount(typeCount);
        result.setProductCount(productCount);
        result.setLowerCount(lowerProductCount);
        return result;
    }

    @Override
    public List<ProductType> getProductTypeListByTypeId(Long typeid) {
        return productTypeRepository.findByTypeId(typeid);
    }

    @Override
    public ProductType getProductType(Long productid, Long typeid) {
        List<ProductType> productTypeList = productTypeRepository.findByProductIdAndTypeId(productid, typeid);
        if(ListUtils.isNotNull(productTypeList)){
            return productTypeList.get(0);
        }
        return null;
    }

    @Override
    public ProductType updateProductType(ProductType productType) {
        return productTypeRepository.saveAndFlush(productType);
    }

    @Override
    public Boolean exitType(Long id) {
        return typeRepository.existsById(id);
    }

    @Override
    public Boolean updateTypeFall(Type type)throws LikefoodException {
        Long sort = type.getSort();
        Type after = typeRepository.findFirstByStatusAndSortAfterOrderBySort(ConstantValue.T_TYPE_STATUS_ZC, sort);
        if(after == null){
            throw new LikefoodException(MsgValue.ALEADY_LAST);
        }
        type.setSort(after.getSort());
        after.setSort(sort);
        updateType(after);
        updateType(type);
        return true;
    }


    @Override
    public Boolean updateProductFall(ProductType productType)throws LikefoodException {
        Long sort = productType.getSort();
        List<Product> productList = productRepository.findByTypeidOrderBySort(productType.getTypeId());     // 获得此type下正常的product
        List<Long> productIdList = getProductLongList(productList);
        if(ListUtils.isNull(productIdList)){
            throw new LikefoodException(MsgValue.NOT_PRODUCT_BY_TYPE);
        }
        ProductType after = productTypeRepository.findFirstByProductIdInAndTypeIdAndSortAfterOrderBySort(productIdList, productType.getTypeId(), sort);
        if(after == null){
            throw new LikefoodException(MsgValue.ALEADY_LAST);
        }
        productType.setSort(after.getSort());
        after.setSort(sort);
        updateProductType(after);
        updateProductType(productType);
        return true;
    }
    public List<Long> getProductLongList(List<Product> productList){
        List<Long> result = new ArrayList<>();
        if(ListUtils.isNotNull(productList)){
            for(Product product : productList){
                result.add(product.getId());
            }
        }
        return result;
    }
    @Override
    public Boolean updateTypeRise(Type type) throws LikefoodException {
        Long sort = type.getSort();
        Type before = typeRepository.findFirstByStatusAndSortBeforeOrderBySortDesc(ConstantValue.T_TYPE_STATUS_ZC, sort);
        if(before == null ){     // 如果之前没有分类或者之前的分类为0
            throw new LikefoodException(MsgValue.ALEADY_FIRST);
        }
        if(before.getSort().intValue() == 0){
            throw new LikefoodException(MsgValue.NOT_RISE_WFL);
        }
        type.setSort(before.getSort());
        before.setSort(sort);
        updateType(before);
        updateType(type);
        return true;
    }

    @Override
    public Boolean updateProductRise(ProductType productType)throws LikefoodException {
        Long sort = productType.getSort();
        List<Product> productList = productRepository.findByTypeidOrderBySort(productType.getTypeId());     // 获得此type下正常的product
        List<Long> productIdList = getProductLongList(productList);
        if(ListUtils.isNull(productIdList)){
            throw new LikefoodException(MsgValue.NOT_PRODUCT_BY_TYPE);
        }
        ProductType after = productTypeRepository.findFirstByProductIdInAndTypeIdAndSortBeforeOrderBySortDesc(productIdList, productType.getTypeId(), sort);
        if(after == null){
            throw new LikefoodException(MsgValue.ALEADY_FIRST);
        }
        productType.setSort(after.getSort());
        after.setSort(sort);
        updateProductType(after);
        updateProductType(productType);
        return true;
    }

    @Override
    public List<Product> getProductListOrderByUpdatetimeDesc(Long status) {
        return productRepository.findByStatusOrderByUpdatetimeDesc(status);
    }

    @Override
    public List<Type> getTypeListOrderByUpdatetimeDesc(Long status) {
        return typeRepository.findByStatusOrderByUpdatetimeDesc(status);
    }

    @Override
    public List<TypeDtoV> getTypeLisView() {
        List<TypeDtoV> result = new ArrayList<>();
        List<Type> typeList = getTypeList(ConstantValue.T_TYPE_STATUS_ZC);
        if(ListUtils.isNotNull(typeList)){
            for(Type type : typeList){
                TypeDtoV typeDtoV = new TypeDtoV();
                typeDtoV.setId(type.getId());
                typeDtoV.setName(type.getName());
                if(type.getId().equals(ConstantValue.T_TYPE_ID_WFL)){
                    typeDtoV.setProductCount(productRepository.countWflProduct());
                }else{
                    typeDtoV.setProductCount(productRepository.countByTypeid(type.getId()));
                }
                result.add(typeDtoV);
            }
        }
        return result;
    }

    @Override
    public Page<Type> getTypePageOrderByUpdatetimeDesc(Long status, Pageable pageable) {
        return typeRepository.findByStatusOrderByUpdatetimeDesc(status, pageable);
    }

    @Override
    public Page<Type> getTypePage(Long status, Pageable pageable) {
        return typeRepository.findByStatusOrderBySort(status, pageable);
    }

    @Override
    public List<Type> getTypeList(Long status) {
        return typeRepository.findByStatusOrderBySort(status);
    }

    @Override
    public List<ProductWechatV> getProductWechatVList() {
        List<ProductWechatV> result = new ArrayList<>();
        List<Type> typeList = typeRepository.findByStatusOrderBySort(ConstantValue.T_TYPE_STATUS_ZC);
        if(ListUtils.isNotNull(typeList)){
            int len = typeList.size();
            for(int i = 1; i < len; i++){       // 从第一个开始遍历
                ProductWechatV productWechatV = new ProductWechatV();
                productWechatV.setTypeid(typeList.get(i).getId());
                productWechatV.setTypeName(typeList.get(i).getName());
                List<Product> productList = getProductListByTypeidOrderBySort(typeList.get(i).getId());
                productWechatV.setProductList(productList);
                result.add(productWechatV);
            }
        }

        return result;
    }

    @Override
    public Page<Product> getWflProductPage(Pageable pageable) {
        return productRepository.findWflProduct(pageable);
    }

    @Override
    public List<Product> getWflProduct() {
        return productRepository.findWflProduct();
    }

    @Override
    public Page<Product> getProductPageByTypeid(Long typeid, Pageable pageable) {
        return productRepository.findByTypeidPage(typeid, pageable);
    }

    @Override
    public List<Product> getProductListByTypeidOrderBySort(Long typeid) {
        return productRepository.findByTypeidOrderBySort(typeid);
    }

    @Override
    public Page<Product> getProductPage(Long status, Pageable pageable) {
        return productRepository.findByStatus(status, pageable);
    }

    @Override
    public List<Product> getProductList(Long status) {
        return productRepository.findByStatusOrderByIdDesc(status);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findByIdAndIdNotNull(productId);
    }

    @Override
    public ProductDtoV getProductDetail(Long productId) {
        Product product = getProduct(productId);
        ProductDtoV result = productDtoConverter.buildProductDtoV(product);
        List<Type> typeList = typeRepository.findByProductId(productId);
        result.setTypeList(typeList);
        return result;
    }

    @Override
    public ProductDtoV getProductDetail(Product product) {
        ProductDtoV result = productDtoConverter.buildProductDtoV(product);
        List<Type> typeList = typeRepository.findByProductId(product.getId());
        result.setTypeList(typeList);
        return result;
    }

    @Override
    public Product getProductDetailNotDel(Long productId) {
        return productRepository.findByIdAndStatus(productId, ConstantValue.T_PRODUCT_STATUS_ZC);
    }

    @Override
    public ProductDtoV updateProductStock(ProductStockDto productStockDto, Product product) {
        product.setStock(productStockDto.getStock());
        Product result = updateProduct(product);
        return getProductDetail(result);
    }

    @Override
    public synchronized ProductDtoV updateProduct(ProductDtoU productDtoU, Product product, List<Type> typeList) {
        productDtoConverter.buildProduct(productDtoU, product);
        Product pro = updateProduct(product);
        List<ProductType> productTypeList = productTypeRepository.findByProductId(productDtoU.getId());     // 原来的
        List<Type> typeListResult = new ArrayList<>();
        typeListResult.addAll(typeList);
        for(ProductType productType : productTypeList){     // 遍历类型
            boolean hasType = false;
            for(Type type : typeList){
                if(productType.getTypeId().equals(type.getId())){       // 如果两个类型相同
                    hasType = true;
                    typeList.remove(type);      // 如果能找到，移除掉此type
                    break;
                }
            }
            if(!hasType){       // 原来的类型有，现在没有了，表示删除了原来的
                productTypeRepository.delete(productType);
            }
        }

        addProductType(typeList, product.getId());

        ProductDtoV productDtoV = productDtoConverter.buildProductDtoV(pro);
        productDtoV.setTypeList(typeListResult);
        return productDtoV;
    }

    @Override
    public Product updateProduct(Product product) {
        product.setUpdatetime(new Date());
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Type updateType(TypeDtoU typeDtoU, Type type) {
        Type result = productDtoConverter.buildType(typeDtoU, type);
        return updateType(result);
    }

    @Override
    public Type updateType(Type type) {
        type.setUpdatetime(new Date());
        return typeRepository.saveAndFlush(type);
    }

    @Override
    public Type getTypeDetail(Long typeId) {
        return typeRepository.findFirstById(typeId);
    }

    @Override
    public Type getTypeDetailNotDel(Long typeId) {
        return typeRepository.findByIdAndStatus(typeId, ConstantValue.T_PRODUCT_STATUS_ZC);
    }

    @Override
    public Type addType(TypeDtoC typeDtoC) {
        Type type = productDtoConverter.buildType(typeDtoC);
        Long count = typeRepository.count();
        type.setSort(count + 1);
        return updateType(type);
    }

    @Override
    public synchronized ProductDtoV addProduct(ProductDtoC productDtoC, List<Type> typeList) {
        Product result = productDtoConverter.buildProduct(productDtoC);
        Product product = updateProduct(result);
        addProductType(typeList, product.getId());
        ProductDtoV productDtoV = productDtoConverter.buildProductDtoV(product);
        productDtoV.setTypeList(typeList);
        return productDtoV;
    }

    public List<ProductType> addProductType(List<Type> typeList, Long productId){
        List<ProductType> result = new ArrayList<>();
        if(ListUtils.isNotNull(typeList)){
            for(Type type : typeList){
                ProductType productType = new ProductType();
                productType.setTypeId(type.getId());
                productType.setProductId(productId);
                ProductType lastProductType = productTypeRepository.findFirstByTypeIdOrderBySortDesc(type.getId());
                if(lastProductType == null){
                    productType.setSort(ConstantValue.T_PRODUCT_TYPE_SORT_INIT);
                }else{
                    productType.setSort(lastProductType.getSort() + 1);
                }
                ProductType re = updateProductType(productType);
                result.add(re);
            }
        }
        return result;

    }
}
