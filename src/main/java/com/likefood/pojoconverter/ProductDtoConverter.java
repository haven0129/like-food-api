package com.likefood.pojoconverter;


import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.ProductType;
import com.likefood.domain.product.model.Type;
import com.likefood.pojo.product.*;

public interface ProductDtoConverter {

    /**
     * 新增产品时，将ProductDtoC的信息映射到Product中
     * @return
     */
    Product buildProduct(ProductDtoC source);

    /**
     * 修改产品时，将ProductDtoU的信息映射到Product中
     * @return
     */
    Product buildProduct(ProductDtoU source, Product result);

    Type buildType(TypeDtoC source);

    Type buildType(TypeDtoU source, Type result);

    ProductDtoV buildProductDtoV(Product source);
}
