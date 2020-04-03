package com.likefood.pojo.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductDtoC {
  @NotBlank
  @ApiModelProperty(value = "点心名称", example = "脏脏包")
  private String name;
  @ApiModelProperty(value = "点心图片的url，多个图片用;分开")
  private String pic;
  @NotBlank
  @ApiModelProperty(value = "点心单位", example = "袋/箱/包")
  private String unit;
  @NotNull
  @ApiModelProperty(value = "点心单价", example = "9.9")
  private Double price;
  @NotNull
  @ApiModelProperty(value = "库存")
  private Long stock;
  @ApiModelProperty(value = "点心描述", example = "10个一袋，新晋网红脏脏包，美味超乎你的想象")
  private String remark;
  @NotEmpty
  @ApiModelProperty(value = "点心类型", example = "[1,2]")
  private List<Long> typeIds;

  public List<Long> getTypeIds() {
    return typeIds;
  }

  public void setTypeIds(List<Long> typeIds) {
    this.typeIds = typeIds;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
