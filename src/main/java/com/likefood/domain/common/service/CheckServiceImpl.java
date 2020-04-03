package com.likefood.domain.common.service;

import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.ProductType;
import com.likefood.domain.product.model.Type;
import com.likefood.domain.product.service.ProductService;
import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.User;
import com.likefood.domain.user.service.RoleService;
import com.likefood.domain.user.service.UserService;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.common.MsgValue;
import com.likefood.pojo.product.ProductTypeDto;
import com.likefood.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private ProductService productService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public Type checkType(Long id) throws LikefoodException {
        Type type = productService.getTypeDetail(id);
        if(type == null){       // 如果为空
            throw new LikefoodException(MsgValue.NO_TYPE);
        }
        return type;
    }

    @Override
    public Boolean checkTypeStatus(Long status) throws LikefoodException {
        if(!status.equals(ConstantValue.T_TYPE_STATUS_ZC) && !status.equals(ConstantValue.T_TYPE_STATUS_SC)){
            throw new LikefoodException(MsgValue.TYPE_STATUS_ERROR);
        }
        return true;
    }

    @Override
    public User checkUser(String username) throws LikefoodException {
        User user = userService.findByUserName(username);
        if(user == null){
            throw new LikefoodException(MsgValue.NO_USER_EXIT);
        }
        return user;
    }

    @Override
    public User checkUserDel(Long userId) throws LikefoodException {
        return null;
    }

    @Override
    public User checkUserZc(Long userId) throws LikefoodException {
        return null;
    }

    @Override
    public User checkUser(Long userId) throws LikefoodException {
        User user = userService.findById(userId);
        if(user == null){
            throw new LikefoodException(MsgValue.NO_USER_EXIT);
        }
        return user;
    }

    @Override
    public List<Role> checkRoleList(List<Long> roleIds) throws LikefoodException {
        List<Role> result = new ArrayList<>();
        if(ListUtils.isNotNull(roleIds)){
            for(Long id : roleIds){
                Role role = checkRole(id);
                result.add(role);
            }
            return result;
        }
        throw new LikefoodException(MsgValue.NOT_CHOOSE_ROLE);
    }

    @Override
    public Role checkRole(Long roleId) throws LikefoodException {
        Role role = roleService.getRoleById(roleId);
        if(role == null){
            throw new LikefoodException(MsgValue.NO_ROLE);
        }
        return role;
    }

    @Override
    public Type checkTypeCanDel(Long id) throws LikefoodException {
        Type type = checkTypeNotDel(id);
        List<ProductType> productTypeList = productService.getProductTypeListByTypeId(id);
        if(ListUtils.isNotNull(productTypeList)){
            throw new LikefoodException(MsgValue.CANT_DEL_TYPE_DUETO_HASPRODUCT);
        }
        return type;
    }

    @Override
    public Type checkTypeNotDel(Long id) throws LikefoodException {
        if(ConstantValue.T_TYPE_ID_WFL.equals(id)){
            throw new LikefoodException(MsgValue.CANT_TYPE);
        }
        Type type = productService.getTypeDetailNotDel(id);
        if(type == null){       // 如果为空
            throw new LikefoodException(MsgValue.NO_TYPE_OR_DEL);
        }
        return type;
    }

    @Override
    public Product checkProduct(Long id) throws LikefoodException {
        Product product = productService.getProduct(id);
        if(product == null){       // 如果为空
            throw new LikefoodException(MsgValue.NO_PRODUCT);
        }
        return product;
    }

    @Override
    public List<Type> checkTypeIds(List<Long> typeIds) throws LikefoodException {
        if(ListUtils.isNull(typeIds)){
            throw new LikefoodException(MsgValue.NOT_CHOOSE_TYPE);
        }
        List<Type> result = new ArrayList<>();
        for(Long id : typeIds){
            Type type = checkTypeNotDel(id);        // 检查是否被删除
            result.add(type);
        }
        return result;
    }

    @Override
    public ProductType checkProductTypeNotDel(ProductTypeDto productTypeDto) throws LikefoodException {
        checkTypeNotDel(productTypeDto.getTypeid());
        checkProductNotDel(productTypeDto.getProductid());
        ProductType productType = productService.getProductType(productTypeDto.getProductid(), productTypeDto.getTypeid());
        if(productType == null){
            throw new LikefoodException(MsgValue.NO_PRODUCT_TYPE);
        }
        return productType;
    }

    @Override
    public Product checkProductNotDel(Long id) throws LikefoodException {
        Product product = productService.getProductDetailNotDel(id);
        if(product == null){       // 如果为空
            throw new LikefoodException(MsgValue.NO_PRODUCT_OR_DEL);
        }
        return product;
    }
}
