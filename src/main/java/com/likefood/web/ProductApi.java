package com.likefood.web;

import com.likefood.domain.common.service.CheckService;
import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.ProductType;
import com.likefood.domain.product.model.Type;
import com.likefood.domain.product.service.ProductService;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.common.FileUploadResult;
import com.likefood.pojo.common.MsgValue;
import com.likefood.pojo.common.Result;
import com.likefood.pojo.product.*;
import com.likefood.utils.FileUtils;
import com.likefood.utils.ListUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "/product", tags = "Produce", description = "点心管理")
public class ProductApi {
    @Autowired
    private ProductService productService;
    @Autowired
    private CheckService checkService;

    @ApiOperation(value = "/product/type/add", nickname = "新增类型", notes = "新增类型")
    @RequestMapping(value = "/product/type/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    synchronized Type addType(@Valid@RequestBody TypeDtoC typeDtoC) {
        return productService.addType(typeDtoC);
    }

    @ApiOperation(value = "/product/type/update-productList", nickname = "修改类型的产品列表", notes = "修改类型的产品列表")
    @RequestMapping(value = "/product/type/update-productList", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    synchronized Result updateProductList(@Valid@RequestBody ProductTypeDtoU productTypeDtoU)throws LikefoodException{
        checkService.checkTypeNotDel(productTypeDtoU.getTypeId());
        if(ListUtils.isNotNull(productTypeDtoU.getProductIdList())){
            for(Long productId : productTypeDtoU.getProductIdList()){
                checkService.checkProductNotDel(productId);
            }
        }
        Boolean result = productService.updateProductListByTypeid(productTypeDtoU);
        if(result){
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }else{
//            return new Result(MsgValue.FALSE, MsgValue.FAIL);
            throw new LikefoodException(MsgValue.FAIL);
        }
    }

    @ApiOperation(value = "/product/type/update", nickname = "修改类型", notes = "修改类型")
    @RequestMapping(value = "/product/type/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Type updateType(@Valid@RequestBody TypeDtoU typeDtoU) throws LikefoodException{
        Type type = checkService.checkTypeNotDel(typeDtoU.getId());
        return productService.updateType(typeDtoU, type);
    }

    @ApiOperation(value = "/product/type/delete", nickname = "删除类型", notes = "删除类型")
    @RequestMapping(value = "/product/type/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Type deleteType(@Valid@RequestBody TypeIdDto typeIdDto) throws LikefoodException{
        Type type = checkService.checkTypeCanDel(typeIdDto.getId());
        type.setStatus(ConstantValue.T_TYPE_STATUS_SC);
        return productService.updateType(type);
    }

    @ApiOperation(value = "/product/type/recovery", nickname = "恢复类型", notes = "恢复类型")
    @RequestMapping(value = "/product/type/recovery", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Type recoveryType(@Valid@RequestBody TypeIdDto typeIdDto) throws LikefoodException{
        Type type = checkService.checkType(typeIdDto.getId());
        type.setStatus(ConstantValue.T_TYPE_STATUS_ZC);
        return productService.updateType(type);
    }

    @ApiOperation(value = "/product/type/detail", nickname = "查看类型详情", notes = "查看类型详情")
    @RequestMapping(value = "/product/type/detail", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Type typeDetail(@RequestParam("typeid") Long typeid){
        return productService.getTypeDetail(typeid);
    }

    @ApiOperation(value = "/product/type/list", nickname = "查看类型列表", notes = "查看类型列表")
    @RequestMapping(value = "/product/type/list", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    List<Type> typeList() throws LikefoodException{
        return productService.getTypeList(ConstantValue.T_TYPE_STATUS_ZC);
    }

    @ApiOperation(value = "/product/type/page", nickname = "分页查看类型列表", notes = "分页查看类型列表")
    @RequestMapping(value = "/product/type/page", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Page<Type> typePage(Pageable pageable) throws LikefoodException{
        return productService.getTypePage(ConstantValue.T_TYPE_STATUS_ZC, pageable);
    }

    @ApiOperation(value = "/product/type/delete-page", nickname = "分页查看删除类型列表", notes = "分页查看删除类型列表")
    @RequestMapping(value = "/product/type/delete-page", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Page<Type> typeDeletePage(Pageable pageable){
        return productService.getTypePage(ConstantValue.T_TYPE_STATUS_SC, pageable);
    }

    @ApiOperation(value = "/product/type/rise", nickname = "类型排序前进1", notes = "类型排序前进1")
    @RequestMapping(value = "/product/type/rise", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Result typeRise(@Valid@RequestBody TypeIdDto typeIdDto)throws LikefoodException{
        Type type = checkService.checkTypeNotDel(typeIdDto.getId());
        Boolean result = productService.updateTypeRise(type);
        if(result){
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }else{
//            return new Result(MsgValue.FALSE, MsgValue.FAIL);
            throw new LikefoodException(MsgValue.FAIL);
        }
    }

    @ApiOperation(value = "/product/type/fall", nickname = "类型排序后退1", notes = "类型排序后退1")
    @RequestMapping(value = "/product/type/fall", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Result typeFall(@Valid@RequestBody TypeIdDto typeIdDto)throws LikefoodException{
        Type type = checkService.checkTypeNotDel(typeIdDto.getId());
        Boolean result = productService.updateTypeFall(type);
        if(result){
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }else{
//            return new Result(MsgValue.FALSE, MsgValue.FAIL);
            throw new LikefoodException(MsgValue.FAIL);
        }
    }

    @ApiOperation(value = "/product/detail", nickname = "查看点心详情", notes = "查看点心详情")
    @RequestMapping(value = "/product/detail", method = RequestMethod.GET, produces = {"application/json"})
    ProductDtoV productDetail(@RequestParam("productId") Long productId) {
        return productService.getProductDetail(productId);
    }

    @ApiOperation(value = "/product/add", nickname = "新增点心", notes = "新增点心")
    @RequestMapping(value = "/product/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    synchronized ProductDtoV addProduct(@Valid@RequestBody ProductDtoC productDtoC)throws LikefoodException {
        List<Type> typeList = checkService.checkTypeIds(productDtoC.getTypeIds());
        return productService.addProduct(productDtoC, typeList);
    }

    @ApiOperation(value = "/product/update", nickname = "修改点心", notes = "修改点心")
    @RequestMapping(value = "/product/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    ProductDtoV updateProduct(@Valid@RequestBody ProductDtoU productDtoU) throws LikefoodException{
        Product product = checkService.checkProductNotDel(productDtoU.getId());
        List<Type> typeList = checkService.checkTypeIds(productDtoU.getTypeIds());
        return productService.updateProduct(productDtoU, product, typeList);
    }

    @ApiOperation(value = "/product/update-stock", nickname = "修改点心的库存", notes = "修改点心的库存")
    @RequestMapping(value = "/product/update-stock", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    ProductDtoV updateProductStock(@Valid@RequestBody ProductStockDto productStockDto) throws LikefoodException{
        Product product = checkService.checkProductNotDel(productStockDto.getId());
        return productService.updateProductStock(productStockDto, product);
    }

    @ApiOperation(value = "/product/lower", nickname = "下架点心", notes = "下架点心")
    @RequestMapping(value = "/product/lower", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    ProductDtoV lowerProduct(@Valid@RequestBody ProductIdDto productIdDto) throws LikefoodException{
        Product product = checkService.checkProductNotDel(productIdDto.getProductId());
        product.setStatus(ConstantValue.T_PRODUCT_STATUS_XJ);
        return productService.getProductDetail(productService.updateProduct(product));
    }

    @ApiOperation(value = "/product/delete", nickname = "删除点心", notes = "删除点心")
    @RequestMapping(value = "/product/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    ProductDtoV deleteProduct(@Valid@RequestBody ProductIdDto productIdDto) throws LikefoodException{
        Product product = checkService.checkProductNotDel(productIdDto.getProductId());
        product.setStatus(ConstantValue.T_PRODUCT_STATUS_SC);
        return productService.getProductDetail(productService.updateProduct(product));
    }

    @ApiOperation(value = "/product/recovery", nickname = "恢复点心", notes = "恢复点心")
    @RequestMapping(value = "/product/recovery", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    ProductDtoV recoveryProduct(@Valid@RequestBody ProductIdDto productIdDto) throws LikefoodException{
        Product product = checkService.checkProduct(productIdDto.getProductId());
        product.setStatus(ConstantValue.T_PRODUCT_STATUS_ZC);
        return productService.getProductDetail(productService.updateProduct(product));
    }

    @ApiOperation(value = "/product/page", nickname = "查看点心列表", notes = "查看点心列表")
    @RequestMapping(value = "/product/page", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'view')")
    Page<Product> productPage(Pageable pageable){
        return productService.getProductPage(ConstantValue.T_PRODUCT_STATUS_ZC, pageable);
    }

    @ApiOperation(value = "/product/recovery-page", nickname = "查看下架点心列表", notes = "查看下架点心列表")
    @RequestMapping(value = "/product/recovery-page", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'view')")
    Page<Product> productRecoveryPage(Pageable pageable){
//        return productService.getProductListOrderByUpdatetimeDesc(ConstantValue.T_PRODUCT_STATUS_XJ);
        return productService.getProductPage(ConstantValue.T_PRODUCT_STATUS_XJ, pageable);
    }

    @ApiOperation(value = "/product/delete-page", nickname = "查看删除点心列表", notes = "查看删除点心列表")
    @RequestMapping(value = "/product/delete-page", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'view')")
    Page<Product> productDeletePage(Pageable pageable){
//        return productService.getProductListOrderByUpdatetimeDesc(ConstantValue.T_PRODUCT_STATUS_SC);
        return productService.getProductPage(ConstantValue.T_PRODUCT_STATUS_SC, pageable);
    }

    @ApiOperation(value = "/product/upload-images", nickname = "上传图片", notes = "上传图片")
    @RequestMapping(value = "/product/upload-images", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    FileUploadResult uploadImages(@RequestParam("file") MultipartFile file) throws IOException, LikefoodException {
        return FileUtils.uploadImage(file);
    }
    @ApiOperation(value = "/product/list-by-type", nickname = "根据类型，查看点心列表（web端用）", notes = "根据类型，查看点心列表（web端用）")
    @RequestMapping(value = "/product/list-by-type", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'view')")
    List<Product> productListByType(@RequestParam("typeid")Long typeid)throws LikefoodException {
        if(typeid.equals(ConstantValue.T_TYPE_ID_WFL)){     // 如果是查询未分类的
            return productService.getWflProduct();
        }else{
            checkService.checkTypeNotDel(typeid);
        }
        return productService.getProductListByTypeidOrderBySort(typeid);
    }

    @ApiOperation(value = "/product/page-by-type", nickname = "根据类型，分页查看点心列表（web端用）", notes = "根据类型，分页查看点心列表（web端用）")
    @RequestMapping(value = "/product/page-by-type", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'view')")
    Page<Product> productPageByType(@RequestParam("typeid")Long typeid, Pageable pageable)throws LikefoodException {
        if(typeid.equals(ConstantValue.T_TYPE_ID_WFL)){     // 如果是查询未分类的
            return productService.getWflProductPage(pageable);
        }else{
            checkService.checkTypeNotDel(typeid);
        }
        return productService.getProductPageByTypeid(typeid, pageable);
    }

    @ApiOperation(value = "/product/count-by-type", nickname = "根据类型，统计产品数目", notes = "根据类型，统计产品数目")
    @RequestMapping(value = "/product/count-by-type", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'view')")
    Long productCountByType(@RequestParam("typeid")Long typeid)throws LikefoodException {
        return productService.productCountByType(typeid);
    }

    @ApiOperation(value = "/product/result", nickname = "一次性查出所有的类型列表以及下面的点心列表（小程序用）", notes = "一次性查出所有的类型列表以及下面的点心列表（小程序用）")
    @RequestMapping(value = "/product/result", method = RequestMethod.GET, produces = {"application/json"})
    List<ProductWechatV> productResult() {
        return productService.getProductWechatVList();
    }

    @ApiOperation(value = "/product/rise", nickname = "点心排序后退1", notes = "点心排序后退1")
    @RequestMapping(value = "/product/rise", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Result productRise(@Valid@RequestBody ProductTypeDto productTypeDto) throws LikefoodException{
        ProductType productType = checkService.checkProductTypeNotDel(productTypeDto);
        Boolean result = productService.updateProductRise(productType);
        if(result){
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }else{
//            return new Result(MsgValue.FALSE, MsgValue.FAIL);
            throw new LikefoodException(MsgValue.FAIL);
        }
    }

    @ApiOperation(value = "/product/fall", nickname = "点心排序前进1", notes = "点心排序前进1")
    @RequestMapping(value = "/product/fall", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Result productFall(@Valid@RequestBody ProductTypeDto productTypeDto) throws LikefoodException{
        ProductType productType = checkService.checkProductTypeNotDel(productTypeDto);
        Boolean result = productService.updateProductFall(productType);
        if(result){
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }else{
//            return new Result(MsgValue.FALSE, MsgValue.FAIL);
            throw new LikefoodException(MsgValue.FAIL);
        }
    }

    /*@ApiOperation(value = "/product/type-list", nickname = "查看类型列表(首页)", notes = "查看类型列表(首页)")
    @RequestMapping(value = "/product/type-list", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    List<TypeDtoV> typeListView(){
        return productService.getTypeLisView();
    }*/
    @ApiOperation(value = "/product/count", nickname = "统计", notes = "统计")
    @RequestMapping(value = "/product/count", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    ProductCountDto productCount(){
       return productService.productCount();
    }
}
