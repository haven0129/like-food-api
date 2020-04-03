package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductTypeDtoU {
  @NotNull
  @ApiModelProperty(value = "类型Id", example = "1")
  private Long typeId;
  @ApiModelProperty(value = "产品id列表", example = "[1,2,3]")
  private List<Long> productIdList;

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public List<Long> getProductIdList() {
    return productIdList;
  }

  public void setProductIdList(List<Long> productIdList) {
    this.productIdList = productIdList;
  }
}
