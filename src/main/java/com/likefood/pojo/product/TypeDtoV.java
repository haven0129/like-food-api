package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TypeDtoV {
  @ApiModelProperty(value = "类型id")
  private Long id;
  @ApiModelProperty(value = "类型名称", example = "热销")
  private String name;
  @ApiModelProperty(value = "类型下的产品个数")
  private Long productCount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getProductCount() {
    return productCount;
  }

  public void setProductCount(Long productCount) {
    this.productCount = productCount;
  }
}
