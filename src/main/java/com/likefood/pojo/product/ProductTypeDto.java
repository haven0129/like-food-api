package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class ProductTypeDto {
  @NotNull
  @ApiModelProperty(value = "点心id")
  private Long productid;
  @NotNull
  @ApiModelProperty(value = "类型id")
  private Long typeid;

  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }

  public Long getTypeid() {
    return typeid;
  }

  public void setTypeid(Long typeid) {
    this.typeid = typeid;
  }
}
