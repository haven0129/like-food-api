package com.likefood.pojo.company;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CompanyDtoU {
  @ApiModelProperty(value = "公司名称", example = "株洲xx食品有限公司")
  @NotBlank
  private String companyname;
  @ApiModelProperty(value = "地址", example = "湖南省株洲市天元区...")
  @NotBlank
  private String addr;
  @ApiModelProperty(value = "联系电话（手机或者电话）", example = "189xxxxxxxx")
  @NotBlank
  private String telephone;
  @NotBlank
  @ApiModelProperty(value = "联系人", example = "黄女士")
  private String attention;
  @ApiModelProperty(value = "图片地址")
  private String pic;
  @ApiModelProperty(value = "配送范围", example = "株洲市")
  @NotBlank
  private String scope;


  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }


  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  public String getAttention() {
    return attention;
  }

  public void setAttention(String attention) {
    this.attention = attention;
  }


  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }


  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }


}
