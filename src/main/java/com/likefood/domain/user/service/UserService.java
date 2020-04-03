package com.likefood.domain.user.service;

import com.likefood.domain.user.model.LoginLog;
import com.likefood.domain.user.model.User;
import com.likefood.pojo.user.UserDtoC;
import com.likefood.pojo.user.UserDtoS;
import com.likefood.pojo.user.UserDtoU;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    /**
     * 保存用户
     * @param user
     * @return
     * @throws DataAccessException
     */
    User save(User user) throws DataAccessException;

    /**
     * 修改用户
     * @param user
     * @param userDtoU
     * @return
     * @throws DataAccessException
     */
    User updateUser(User user, UserDtoU userDtoU) throws DataAccessException;

    /**
     * 第一次添加用户
     * @param userDtoC
     * @return
     * @throws DataAccessException
     */
    User saveUserFirst(UserDtoC userDtoC) throws DataAccessException;

    /**
     * 根据用户名返回用户信息
     * @param userName
     * @return
     * @throws DataAccessException
     */
    User findByUserName(String userName) throws DataAccessException;

    /**
     * 根据用户ID返回用户信息
     * @param userId
     * @return
     */
    User findById(Long userId);

    /**
     * 根据用户ID和状态返回用户信息
     * @param userId
     * @param status
     * @return
     */
    User findByIdAndStatus(Long userId, Long status);

    /**
     * 重置密码
     * @param user
     * @return
     */
    Boolean resetPassword(User user);

    /**
     * 根据角色ID返回用户信息（分页）
     * @param roleid
     * @param pageable
     * @return
     */
    Page<User> findByRoleidPage(Long roleid, Pageable pageable);

    /**
     * 根据角色ID返回已删除用户信息（分页）
     * @param roleid
     * @param pageable
     * @return
     */
    Page<User> findDelByRoleidPage(Long roleid, Pageable pageable);

    /**
     * 记录登录日志信息
     * @param loginLog
     * @return
     */
    LoginLog save(LoginLog loginLog);

    /**
     * 根据条件查询用户信息（分页）
     * @param userDtoS
     * @param pageable
     * @return
     */
    Page<User> findUserPage(UserDtoS userDtoS, Pageable pageable);

}
