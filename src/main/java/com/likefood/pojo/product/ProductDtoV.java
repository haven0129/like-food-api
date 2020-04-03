package com.likefood.pojo.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likefood.domain.product.model.Type;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ProductDtoV {
  @ApiModelProperty(value = "点心id")
  private Long id;
  @ApiModelProperty(value = "点心名称", example = "脏脏包")
  private String name;
  @ApiModelProperty(value = "点心图片的url，多个图片用;分开")
  private String pic;
  @ApiModelProperty(value = "点心单位", example = "袋/箱/包")
  private String unit;
  @ApiModelProperty(value = "点心单价", example = "9.9")
  private Double price;
  @ApiModelProperty(value = "库存")
  private Long stock;
  @ApiModelProperty(value = "点心描述", example = "10个一袋，新晋网红脏脏包，美味超乎你的想象")
  private String remark;
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  @ApiModelProperty(value = "创建时间")
  private Date createtime;
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  @ApiModelProperty(value = "修改时间")
  private Date updatetime;
  @Column(name = "status")
  @ApiModelProperty(value = "状态：0正常，998下架，999删除")
  private Long status;
  @ApiModelProperty(value = "点心所属类型列表")
  private List<Type> typeList;

  public List<Type> getTypeList() {
    return typeList;
  }

  public void setTypeList(List<Type> typeList) {
    this.typeList = typeList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
    this.updatetime = updatetime;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
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
