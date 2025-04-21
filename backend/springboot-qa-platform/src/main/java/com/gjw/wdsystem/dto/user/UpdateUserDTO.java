package com.gjw.wdsystem.dto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateUserDTO {
    private String id;
    private MultipartFile headPortrait;
    private String username;
    private String introduce;
    private String gender;
    private int age;
}
