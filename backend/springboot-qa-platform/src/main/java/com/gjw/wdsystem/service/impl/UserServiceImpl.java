package com.gjw.wdsystem.service.impl;

import com.gjw.wdsystem.annotation.CurrentUser;
import com.gjw.wdsystem.dto.user.LoginDTO;
import com.gjw.wdsystem.dto.user.RegisterDTO;
import com.gjw.wdsystem.dto.security.UserDetailsImpl;
import com.gjw.wdsystem.dto.user.UpdateUserBaseInfoDTO;
import com.gjw.wdsystem.dto.user.UpdateUserDTO;
import com.gjw.wdsystem.exception.WdSystemException;
import com.gjw.wdsystem.mapper.FollowMapper;
import com.gjw.wdsystem.mapper.UserMapper;
import com.gjw.wdsystem.pojo.Follow;
import com.gjw.wdsystem.pojo.User;
import com.gjw.wdsystem.service.UserService;
import com.gjw.wdsystem.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String BASE64_ENCODE_ERROR_MESSAGE = "用户头像Base64编码异常!";

    private static final String BASE64_PREFIX = "data:image/png;base64,";

    @Override
    public ResponseEntity<Object> getCurrentUser(@CurrentUser User user) {
        try {
            user.setHeadPortraitToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(user.getHeadPortrait().readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
        }
        return ResponseEntity.ok(this.buildResult("获取成功!", user));
    }

    @Override
    public ResponseEntity<Object> getUserById(String userId) {
        User user = userMapper.getUserById(userId);
        try {
            user.setHeadPortraitToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(user.getHeadPortrait().readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
        }
        return ResponseEntity.ok(this.buildResult("获取成功!", user));
    }

    @Override
    public ResponseEntity<Object> getRecommendedUser() {
        List<User> users = userMapper.getRecommendedUser();
        users.forEach(user ->{
            try {
                user.setHeadPortraitToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(user.getHeadPortrait().readAllBytes()));
            } catch (IOException e) {
                throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
            }
        });
        return ResponseEntity.ok(this.buildResult("获取成功",users));
    }

    @Override
    public ResponseEntity<Object> getHeadPortrait(String userId) {
        try {
            String headPortrait = BASE64_PREFIX+Base64.getEncoder().encodeToString(userMapper.getHeadPortraitByUserId(userId).readAllBytes());
            return ResponseEntity.ok(this.buildResult("获取成功",headPortrait));
        } catch (IOException e) {
            throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE+e);
        }
    }

    @Override
    public ResponseEntity<Object> modifyHeadPortrait(String userId, MultipartFile headPortrait) {
        try {
            userMapper.updateHeadPortraitByUserId(userId,headPortrait.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("修改头像时getInputStream()失败",e);
        }
        return ResponseEntity.ok(this.buildResult("修改成功",null));
    }

    @Override
    public ResponseEntity<Object> login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authentication)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResult("用户名或密码错误!",null));
        }
        var result = new HashMap<String,Object>();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        result.put("id",userDetails.getId());
        // 不返回头像了
//        try {
//            result.put("headPortrait",BASE64_PREFIX+Base64.getEncoder().encodeToString(userDetails.getHeadPortrait().readAllBytes()));
//        } catch (IOException e) {
//            throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
//        }
        result.put("username", userDetails.getUsername());
        result.put("role",userDetails.getRole());
        result.put("authority",userDetails.getRole());
        result.put("token", JwtUtil.generateToken(userDetails.getUsername()));// 签发token
        return ResponseEntity.ok(buildResult("登录成功!",result));
    }

    @Override
    public ResponseEntity<Object> register(RegisterDTO registerDTO) {
        if(Objects.nonNull(userMapper.getUserByUsername(registerDTO.getUsername())))
            throw new IllegalArgumentException(registerDTO.getUsername() + " is already registered.");

        User user = new User();
        BeanUtils.copyProperties(registerDTO,user);
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Password Encryption
        if(Objects.isNull(user.getHeadPortrait())){
            // 设置默认头像
            Resource resource = resourceLoader.getResource("classpath:static/default_headPortrait.jpg");
            try {
                user.setHeadPortrait(resource.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("获取默认头像失败",e);
            }
        }
        if(user.getAge() == 0) user.setAge(18); // 设置默认值
        int affectedRows = userMapper.registerUser(user);
        if(affectedRows > 0) return ResponseEntity.ok(this.buildResult("注册成功!",null));
        throw new WdSystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"注册失败,请重试!");
    }

    @Override
    public ResponseEntity<Object> modifyUserById(UpdateUserDTO updateUserDTO) throws IOException {
        int affectedRows = userMapper.modifyUserById(updateUserDTO,updateUserDTO.getHeadPortrait().getInputStream()); // 头像转换为流存入数据库
        return ResponseEntity.ok(this.buildResult("修改成功!",null));
    }

    @Override
    public ResponseEntity<Object> modifyBaseInfo(UpdateUserBaseInfoDTO updateUserBaseInfoDTO) {
        int affectedRows = userMapper.modifyUserBaseInfoById(updateUserBaseInfoDTO);
        if(affectedRows > 0) return ResponseEntity.ok(this.buildResult("修改成功",JwtUtil.generateToken(updateUserBaseInfoDTO.getUsername())));
        throw new WdSystemException("修改失败");
    }

    /**
     * 构造 返回结果集 方法
     * @Nullable 主要用于指示一个方法、属性或参数可以为空值。
     * @param message
     * @param data
     * @return
     */
    private HashMap<String,Object> buildResult(String message,@Nullable Object data){
        var result = new HashMap<String,Object>();
        result.put("message",message);
        result.put("data",data);
        return result;
    }
}
