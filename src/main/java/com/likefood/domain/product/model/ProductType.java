package com.likefood.domain.product.model;

import javax.persistence.*;

@Entity
@Table(name = "T_PRODUCT_TYPE")
public class ProductType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "product_id")
  private Long productId;
  @Column(name = "type_id")
  private Long typeId;
  @Column(name = "sort")
  private Long sort;

  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }
}
