package com.gjw.wdsystem.service;


import com.gjw.wdsystem.annotation.CurrentUser;
import com.gjw.wdsystem.dto.user.LoginDTO;
import com.gjw.wdsystem.dto.user.RegisterDTO;
import com.gjw.wdsystem.dto.user.UpdateUserBaseInfoDTO;
import com.gjw.wdsystem.dto.user.UpdateUserDTO;
import com.gjw.wdsystem.pojo.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    ResponseEntity<Object> getCurrentUser(@CurrentUser User user);
    ResponseEntity<Object> getUserById(String userId);
    ResponseEntity<Object> getRecommendedUser();
    ResponseEntity<Object> getHeadPortrait(String userId);
    ResponseEntity<Object> modifyHeadPortrait(String userId, MultipartFile headPortrait);
    ResponseEntity<Object> login(LoginDTO loginDTO);
    ResponseEntity<Object> register(RegisterDTO registerDTO);
    ResponseEntity<Object> modifyUserById(UpdateUserDTO updateUserDTO) throws IOException;
    ResponseEntity<Object> modifyBaseInfo(UpdateUserBaseInfoDTO updateUserBaseInfoDTO);
}
