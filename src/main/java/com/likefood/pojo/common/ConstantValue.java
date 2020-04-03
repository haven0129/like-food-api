package com.likefood.pojo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantValue {
    public static String savePath;
    @Value("${utils.savePath}")
    public void setSavePath(String savePath) {
        ConstantValue.savePath = savePath;
    }

    public static final Long SUCCESS_RESULT = 1L;     //
    public static final Long FAIL_RESULT = 0L;     //

    public static final Long T_USER_STATUS_ZC = 0L;     // 正常用户状态
    public static final Long T_USER_STATUS_SC = 999L;     // 删除用户状态
    public static final Long T_PRODUCT_STATUS_ZC = 0L;       // 点心正常状态
    public static final Long T_PRODUCT_STATUS_XJ = 998L;     // 点心下架状态
    public static final Long T_PRODUCT_STATUS_SC = 999L;     // 点心删除状态
    public static final Long T_TYPE_STATUS_ZC = 0L;          // 类型正常状态
    public static final Long T_TYPE_STATUS_SC = 999L;        // 类型删除状态
    public static final Long T_TYPE_ID_WFL = 0L;        // 未分类的Id

    public static final Long T_USER_STATUS_INIT = 0L;        // 用户表初始化状态
    public static final String INIT_PASSWORD = "123456";        // 初始化密码
    public static final Long ADMIN_ROLE_ID = 1L;        // 初始化角色
    public static final Long COMPANY_ID = 1L;        // 初始化密码

    public static final int CODE_VALID_DATE = 5*60;     // 验证码有效时间：5分钟


    public static final String T_TYPE_IDS_FG = ";";        // 多个id的分隔符号
    public static final Long T_PRODUCT_TYPE_SORT_INIT = 1L;        // 排序开头为1


    public static final String IMG_PATH = "/images";     // 图片虚拟路径
    public static final String DOWNLOAD_PATH = "/download";     // 下载虚拟路径







}
