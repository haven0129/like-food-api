package com.likefood.web;


import com.likefood.domain.common.service.CheckService;
import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.User;
import com.likefood.domain.user.service.RoleService;
import com.likefood.domain.user.service.UserService;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.common.MsgValue;
import com.likefood.pojo.common.Result;
import com.likefood.pojo.common.SessionResult;
import com.likefood.pojo.user.*;
import com.likefood.pojoconverter.UserDtoConverter;
import com.likefood.utils.StringUtils;
import com.likefood.utils.message.SendMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
@Api(value = "/user", tags = "User", description = "用户管理")
public class UserApi {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CheckService checkService;
    @Autowired
    private UserDtoConverter userDtoConverter;
    @Autowired
    private SendMsg sendMsg;

    @ApiOperation(value = "/user/role/list", nickname = "获得角色列表", notes = "获得角色列表")
    @RequestMapping(value = "/user/role/list", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    List<Role> roleList() {
        return roleService.getRoleList();
    }

    @ApiOperation(value = "/user/add", nickname = "新增用户", notes = "新增用户")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    UserDtoV addUser(@Valid@RequestBody UserDtoC userDtoC) throws LikefoodException{
        List<Role> roleList = checkService.checkRoleList(userDtoC.getRoleIds());
        User user = userService.saveUserFirst(userDtoC);
        return userDtoConverter.buildUserDtoV(user, roleList);
    }

    @ApiOperation(value = "/user/detail", nickname = "查看用户详情", notes = "查看用户详情")
    @RequestMapping(value = "/user/detail", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    UserDtoV userDetail(@RequestParam("userid")Long userid) throws LikefoodException{
        User user = checkService.checkUser(userid);
        List<Role> roleList = roleService.getRoles(userid);
        return userDtoConverter.buildUserDtoV(user, roleList);
    }

    @ApiOperation(value = "/user/list-by-roleid", nickname = "根据角色id获得正常用户列表", notes = "根据角色id获得正常用户列表")
    @RequestMapping(value = "/user/list-by-roleid", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    Page<User> userListByRoleId(@RequestParam("roleId")Long roleId, Pageable pageable) throws LikefoodException{
        checkService.checkRole(roleId);
        return userService.findByRoleidPage(roleId, pageable);
    }

    @ApiOperation(value = "/user/page", nickname = "根据条件分页查询用户", notes = "根据条件分页查询用户")
    @RequestMapping(value = "/user/page", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    Page<User> userPage(@RequestBody UserDtoS userDtoS, Pageable pageable) throws LikefoodException{
        if(userDtoS.getRoleId() != null){
            checkService.checkRole(userDtoS.getRoleId());
        }
        return userService.findUserPage(userDtoS, pageable);
    }

    /*@ApiOperation(value = "/user/delete-page", nickname = "根据条件分页查询已删除用户", notes = "根据条件分页查询已删除用户")
    @RequestMapping(value = "/user/delete-page", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    Page<User> deleteUserPage(@RequestBody UserDtoS userDtoS, Pageable pageable) throws LikefoodException{
        if(userDtoS.getRoleId() != null){
            checkService.checkRole(userDtoS.getRoleId());
        }
        return userService.findUserPage(userDtoS, pageable, ConstantValue.T_USER_STATUS_SC);
    }*/

    @ApiOperation(value = "/user/delete", nickname = "删除用户", notes = "删除用户")
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    Result deleteUser(@Valid@RequestBody UserIdDto userIdDto, @SessionAttribute("userid")Long userid) throws LikefoodException{
        if(userIdDto.getUserid().equals(userid)){
            throw new LikefoodException(MsgValue.CANT_DEL_SELF);
        }
        User user = checkService.checkUser(userIdDto.getUserid());
        user.setStatus(ConstantValue.T_USER_STATUS_SC);
        userService.save(user);
        return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
    }

    @ApiOperation(value = "/user/recovery", nickname = "恢复用户", notes = "恢复用户")
    @RequestMapping(value = "/user/recovery", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    Result recoveryUser(@Valid@RequestBody UserIdDto userIdDto) throws LikefoodException{
        User user = checkService.checkUser(userIdDto.getUserid());
        user.setStatus(ConstantValue.T_USER_STATUS_ZC);
        userService.save(user);
        return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
    }

    @ApiOperation(value = "/user/update", nickname = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    UserDtoV updateUser(@Valid@RequestBody UserDtoU userDtoU) throws LikefoodException{
        List<Role> roleList = checkService.checkRoleList(userDtoU.getRoleIds());
        User user = checkService.checkUser(userDtoU.getId());
        user = userService.updateUser(user, userDtoU);
        return userDtoConverter.buildUserDtoV(user, roleList);
    }

    @ApiOperation(value = "/user/reset-password", nickname = "重置用户的密码", notes = "重置用户的密码")
    @RequestMapping(value = "/user/reset-password", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'admin')")
    Result resetPassword(@RequestBody UserIdDto userIdDto) throws LikefoodException{
        User user = checkService.checkUser(userIdDto.getUserid());
        Boolean result = userService.resetPassword(user);
        if(result) {
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }
//        return new Result(MsgValue.FALSE, MsgValue.FAIL);
        throw new LikefoodException(MsgValue.FAIL);
    }

    @ApiOperation(value = "/user/update-password", nickname = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/user/update-password", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('user', 'view')")
    Result updatePassword(@Valid@RequestBody UpdatePasswordDto updatePasswordDto, @SessionAttribute("userid")Long userid)throws LikefoodException {
        User user = userService.findById(userid);
        if (!passwordEncoder.matches(updatePasswordDto.getOldPassword(), user.getPassword())) {   // 检查密码是否正确
//            return new Result(MsgValue.FALSE, MsgValue.ERROR_PASSWORD);
            throw new LikefoodException(MsgValue.ERROR_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
        userService.save(user);
        return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
    }

    @ApiOperation(value = "/user/code", nickname = "获得修改密码验证码", notes = "获得修改密码验证码")
    @RequestMapping(value = "/user/code", method = RequestMethod.POST, produces = {"application/json"})
    SessionResult getCode(@Valid @RequestBody MobileDto mobileDto, HttpServletRequest request) throws Exception {
        String mobild = mobileDto.getMobile();
        User user = checkService.checkUser(mobild);

        if(StringUtils.isEmptyOrNull(user.getMobile())){
            throw new LikefoodException(MsgValue.NOT_BIND_MOBILE);
        }
        String result = sendMsg.sentValidCode(mobild);
        if(!StringUtils.isEmptyOrNull(result)){
            HttpSession session = request.getSession(true);
            session.setAttribute("mobile", mobild);
            session.setAttribute("code", result);
            System.out.println(result);
            session.setMaxInactiveInterval(ConstantValue.CODE_VALID_DATE);   // 设置五分钟失效时间
            return new SessionResult(MsgValue.TRUE, MsgValue.SUCCESS, session.getId());
        }
//        return new SessionResult(MsgValue.FALSE, MsgValue.FAIL, "");
        throw new LikefoodException(MsgValue.FAIL);
    }
    @ApiOperation(value = "/user/update-password-by-code", nickname = "根据验证码修改密码", notes = "根据验证码修改密码")
    @RequestMapping(value = "/user/update-password-by-code", method = RequestMethod.POST, produces = {"application/json"})
    Result updatePasswordByCode(@Valid @RequestBody UpdatePasswordByCodeDto updatePasswordByCodeDto, HttpServletRequest request,
                                @SessionAttribute("mobile")String mobile, @SessionAttribute("code") String code) throws LikefoodException {
        User user = checkService.checkUser(updatePasswordByCodeDto.getMobile());
        if(! (updatePasswordByCodeDto.getMobile().equals(mobile) &&
                updatePasswordByCodeDto.getCode().equals(code))){   // 此处校验证码
            throw new LikefoodException(MsgValue.ERROR_CODE);
        }
        HttpSession session = request.getSession(false);
        session.invalidate();// 清除此session
        user.setPassword(passwordEncoder.encode(updatePasswordByCodeDto.getNewPassword()));
        User result = userService.save(user);
        if (result != null) {
            return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
        }
//        return new Result(MsgValue.FALSE, MsgValue.FAIL);
        throw new LikefoodException(MsgValue.FAIL);
    }

}
