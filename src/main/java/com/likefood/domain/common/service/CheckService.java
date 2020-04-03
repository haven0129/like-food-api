package com.likefood.domain.common.service;

import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.ProductType;
import com.likefood.domain.product.model.Type;
import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.User;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.product.ProductTypeDto;

import java.util.List;

public interface CheckService {
    Product checkProduct(Long id) throws LikefoodException;
    Type checkType(Long id) throws LikefoodException;

    Product checkProductNotDel(Long id) throws LikefoodException;
    ProductType checkProductTypeNotDel(ProductTypeDto productTypeDto) throws LikefoodException;
    List<Type> checkTypeIds(List<Long> typeIds) throws LikefoodException;
    Type checkTypeNotDel(Long id) throws LikefoodException;
    Type checkTypeCanDel(Long id) throws LikefoodException;
    Role checkRole(Long roleId) throws LikefoodException;
    List<Role> checkRoleList(List<Long> roleIds) throws LikefoodException;
    User checkUser(Long userId) throws LikefoodException;
    User checkUserDel(Long userId) throws LikefoodException;
    User checkUserZc(Long userId) throws LikefoodException;
    User checkUser(String username) throws LikefoodException;
    Boolean checkTypeStatus(Long status)throws LikefoodException;
}
