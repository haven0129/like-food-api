package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class ProductStockDto {
  @NotNull
  @ApiModelProperty(value = "点心id")
  private Long id;
  @NotNull
  @ApiModelProperty(value = "点心库存")
  private Long stock;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }
}
