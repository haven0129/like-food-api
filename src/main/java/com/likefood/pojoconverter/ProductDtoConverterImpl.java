package com.likefood.pojoconverter;

import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.Type;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.product.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductDtoConverterImpl implements ProductDtoConverter {


    @Override
    public ProductDtoV buildProductDtoV(Product source) {
        ProductDtoV result = new ProductDtoV();
        BeanUtils.copyProperties(source, result);
        return result;
    }

    @Override
    public Type buildType(TypeDtoU source, Type result) {
        result.setName(source.getName());
        result.setRemark(source.getRemark());
        return result;
    }

    @Override
    public Type buildType(TypeDtoC source) {
        Type result = new Type();
        BeanUtils.copyProperties(source, result);
        Date date = new Date();
        result.setCreatetime(date);
        result.setUpdatetime(date);
        result.setStatus(ConstantValue.T_TYPE_STATUS_ZC);
        return result;
    }

    @Override
    public Product buildProduct(ProductDtoU source, Product result) {
        result.setUpdatetime(new Date());
        result.setUnit(source.getUnit());
        result.setRemark(source.getRemark());
        result.setStock(source.getStock());
        result.setPrice(source.getPrice());
        result.setPic(source.getPic());
        result.setName(source.getName());
        return result;
    }

    @Override
    public Product buildProduct(ProductDtoC source) {
        Product result = new Product();
        BeanUtils.copyProperties(source, result);
        result.setStatus(Long.valueOf(0));      // 新增产品状态为0
        result.setPraise(Long.valueOf(0));      // 新增产品点赞为0
        Date date = new Date();
        result.setCreatetime(date);
        result.setUpdatetime(date);
        return result;
    }
}