package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PasswordRequestModel {
    private UUID member_id;
    private String curent_password;
    private String new_password;
    private int mode;

    public UserModel toUserModel() {
        UserModel userModel = new UserModel();
        userModel.setMember_id(member_id);
        userModel.setMember_password(new_password);
        return userModel;
    }
}
