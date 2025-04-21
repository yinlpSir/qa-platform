package com.gjw.wdsystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String headPortrait;
    private String username;
    private String password;
    private String phoneNumber;
    private String gender;
    private int age;
}
