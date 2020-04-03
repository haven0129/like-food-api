package com.likefood.web;


import com.likefood.domain.user.model.LoginLog;
import com.likefood.domain.user.model.User;
import com.likefood.domain.user.service.UserService;
import com.likefood.exception.LikefoodException;
import com.likefood.pojo.common.MsgValue;
import com.likefood.pojo.common.Result;
import com.likefood.pojo.login.LoginAddr;
import com.likefood.pojo.login.LoginResponse;
import com.likefood.pojo.user.LoginCredencial;
import com.likefood.pojo.user.UserDto;
import com.likefood.pojoconverter.UserDtoConverter;
import com.likefood.utils.RestTemplateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@RestController
@Api(value = "/login", tags = "Login", description = "登陆管理")
public class LoginApi {
    private static final Logger logger = LoggerFactory.getLogger(LoginApi.class);
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationProvider authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoConverter userDtoConverter;



    @ApiOperation(value = "/login", nickname = "登陆", notes = "web登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json"})
    LoginResponse loginByPassword(HttpServletRequest request, @Valid @RequestBody LoginCredencial credencial) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credencial.getUsername(), credencial.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        session.setMaxInactiveInterval(1*24*60*60);   // 设置1天为失效时间
//        session.setMaxInactiveInterval(20*60);   // 设置20分钟为失效时间
        User user = userService.findByUserName(credencial.getUsername());
        session.setAttribute("userid", user.getId());
        UserDto userInfo = userDtoConverter.buildUserDto(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(session.getId());
        loginResponse.setUserInfo(userInfo);
        LoginAddr loginAddr = getLoginAddr();
        loginLog(user, loginAddr.getIp(), loginAddr.getLoginAddr());
        return loginResponse;
    }

    @ApiOperation(value = "/logout", nickname = "退出登陆", notes = "退出登陆")
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = {"application/json"})
    Result logout(HttpServletRequest request)throws LikefoodException {
        HttpSession session = request.getSession(false);
        if(session == null) {
//            return new Result(MsgValue.FALSE, MsgValue.FAIL);
            throw new LikefoodException(MsgValue.FAIL);
        }
        session.invalidate();
        return new Result(MsgValue.TRUE, MsgValue.SUCCESS);
    }

    public void loginLog(User user, String ip, String addr){
        LoginLog loginLog = new LoginLog();
        loginLog.setUserid(user.getId());
        loginLog.setLoginaddr(addr);
        loginLog.setName(user.getName());
        loginLog.setUsername(user.getUsername());
        loginLog.setLogintype(ip);
        userService.save(loginLog);
    }

    public LoginAddr getLoginAddr(){
        LoginAddr loginAddr = new LoginAddr();
        String url = "http://pv.sohu.com/cityjson?ie=utf-8";
        String data = RestTemplateUtils.getForObject(url , String.class);
        data = data.replace("var returnCitySN = ", "");
        data = data.replace(";", "");
//        System.out.println(data.toString());
        JSONObject json = JSONObject.fromObject(data);
//        System.out.println(json.getString("cip")+json.getString("cname"));
        loginAddr.setIp(json.getString("cip"));
        loginAddr.setLoginAddr(json.getString("cname"));
        return loginAddr;
    }
}
