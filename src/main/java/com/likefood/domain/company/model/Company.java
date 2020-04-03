package com.likefood.domain.company.model;

import javax.persistence.*;

@Entity
@Table(name = "T_COMPANY")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "companyname")
  private String companyname;
  @Column(name = "addr")
  private String addr;
  @Column(name = "telephone")
  private String telephone;
  @Column(name = "attention")
  private String attention;
  @Column(name = "pic")
  private String pic;
  @Column(name = "scope")
  private String scope;
  @Column(name = "lon")
  private String lon;
  @Column(name = "lat")
  private String lat;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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


  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }


  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

}
