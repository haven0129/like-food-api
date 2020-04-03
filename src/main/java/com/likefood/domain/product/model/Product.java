package com.likefood.domain.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likefood.domain.user.model.Role;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_PRODUCT")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "pic")
  private String pic;
  @Column(name = "unit")
  private String unit;
  @Column(name = "price")
  private Double price;
 /* @Column(name = "isnewpro")
  private Long isnewpro;
  @Column(name = "ishotpro")*/
//  private Long ishotpro;
  @Column(name = "status")
  private Long status;
  @Column(name = "stock")
  private Long stock;
  @Column(name = "praise")
  private Long praise;
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
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("sort ASC")
    @JoinTable(
            name="t_product_type",
            joinColumns=@JoinColumn(name="product_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="type_id", referencedColumnName="id", unique=true))
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

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  public Long getPraise() {
    return praise;
  }

  public void setPraise(Long praise) {
    this.praise = praise;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
