package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductIdDto {
  @NotNull
  @ApiModelProperty(value = "点心id")
  private Long productId;

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }
}
