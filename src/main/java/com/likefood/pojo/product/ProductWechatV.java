package com.likefood.pojo.product;

import com.likefood.domain.product.model.Product;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductWechatV {
  private Long typeid;
  private String typeName;
  private List<Product> productList;

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Long getTypeid() {
    return typeid;
  }

  public void setTypeid(Long typeid) {
    this.typeid = typeid;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }
}
