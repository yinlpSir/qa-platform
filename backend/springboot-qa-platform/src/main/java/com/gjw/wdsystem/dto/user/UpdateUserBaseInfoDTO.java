package com.gjw.wdsystem.dto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateUserBaseInfoDTO {
    private String id;
    private String username;
    private String phoneNumber;
    private String introduce;
    private String gender;
    private int age;
}
