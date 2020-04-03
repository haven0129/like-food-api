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
    /**
     * 检查产品是否存在，并返回产品信息
     * @param id
     * @return
     * @throws LikefoodException
     */
    Product checkProduct(Long id) throws LikefoodException;

    /**
     * 检查产品类型是否存在，并返回类型信息
     * @param id
     * @return
     * @throws LikefoodException
     */
    Type checkType(Long id) throws LikefoodException;

    /**
     * 检查产品是否存在并且未被删除，并返回产品信息
     * @param id
     * @return
     * @throws LikefoodException
     */
    Product checkProductNotDel(Long id) throws LikefoodException;

    /**
     * 检查产品类型是否存在并且未被删除，并返回产品类型关联信息
     * @param productTypeDto
     * @return
     * @throws LikefoodException
     */
    ProductType checkProductTypeNotDel(ProductTypeDto productTypeDto) throws LikefoodException;

    /**
     * 根据类型ID列表检查类型是否都存在，并返回类型信息列表
     * @param typeIds
     * @return
     * @throws LikefoodException
     */
    List<Type> checkTypeIds(List<Long> typeIds) throws LikefoodException;

    /**
     * 根据类型ID检查此类型是否未被删除，并返回类型信息
     * @param id
     * @return
     * @throws LikefoodException
     */
    Type checkTypeNotDel(Long id) throws LikefoodException;

    /**
     * 根据类型ID查询类似是否能够被删除，并返回类型信息
     * @param id
     * @return
     * @throws LikefoodException
     */
    Type checkTypeCanDel(Long id) throws LikefoodException;

    /**
     * 根绝角色ID查询角色是否存在，并返回角色信息
     * @param roleId
     * @return
     * @throws LikefoodException
     */
    Role checkRole(Long roleId) throws LikefoodException;

    /**
     * 根据角色ID列表检查角色是否都存在，并返回角色信息列表
     * @param roleIds
     * @return
     * @throws LikefoodException
     */
    List<Role> checkRoleList(List<Long> roleIds) throws LikefoodException;

    /**
     * 根据用户ID检查用户是否存在，并返回用户信息
     * @param userId
     * @return
     * @throws LikefoodException
     */
    User checkUser(Long userId) throws LikefoodException;

    /**
     * 根据用户ID检查用户是否被删除，并返回用户信息
     * @param userId
     * @return
     * @throws LikefoodException
     */
    User checkUserDel(Long userId) throws LikefoodException;

    /**
     * 根据用户ID检查用户是否状态正常，并返回用户信息
     * @param userId
     * @return
     * @throws LikefoodException
     */
    User checkUserZc(Long userId) throws LikefoodException;
    /**
     * 根据用户名检查用户是否存在，并返回用户信息
     * @param username
     * @return
     * @throws LikefoodException
     */
    User checkUser(String username) throws LikefoodException;

    /**
     * 根据类型ID检查类型状态是否正常
     * @param status
     * @return
     * @throws LikefoodException
     */
    Boolean checkTypeStatus(Long status)throws LikefoodException;
}
