package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponseModel {

    private String member_name;
    private String member_password;
    private String member_email;
    private String member_phone;

    public RegisterResponseModel() {

    }

    public RegisterResponseModel(UserModel userModel) {
        member_name = userModel.getMember_name();
        member_password = userModel.getMember_password();
        member_email = userModel.getMember_email();
        member_phone = userModel.getMember_phone();
    }

}
