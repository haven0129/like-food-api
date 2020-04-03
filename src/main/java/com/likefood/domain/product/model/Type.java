package com.likefood.domain.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_TYPE")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "remark")
  private String remark;
  @Column(name = "createtime")
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  private Date createtime;
  @Column(name = "updatetime")
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  private Date updatetime;
  @Column(name = "status")
  private Long status;
  @Column(name = "sort")
  private Long sort;

  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }

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
}
