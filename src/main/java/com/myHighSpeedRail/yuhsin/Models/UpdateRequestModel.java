package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateRequestModel {
    private UUID member_id;
    private String member_name;
    private String member_password;
    private String member_email;
    private String member_phone;

    public UserModel toUserModel() {
        UserModel userModel = new UserModel();
        userModel.setMember_id(member_id);
        userModel.setMember_name(member_name);
        userModel.setMember_password(member_password);
        userModel.setMember_email(member_email);
        userModel.setMember_phone(member_phone);
        return userModel;
    }
}
