package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TypeDtoU {
  @NotNull
  @ApiModelProperty(value = "类型id")
  private Long id;
  @NotBlank
  @ApiModelProperty(value = "类型名称", example = "热销")
  private String name;
  @ApiModelProperty(value = "类型描述", example = "排行前10的产品")
  private String remark;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
