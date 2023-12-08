package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequestModel {
    private String member_name;
    private String member_password;
    private String member_email;
    private String member_phone;

}
