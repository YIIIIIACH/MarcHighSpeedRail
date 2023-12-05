package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UserModel {
    private UUID member_id;
    private String member_name;
    private String member_password;
    private String member_email;
    private String member_phone;
    private long created_at;
    private long update_at;
    private long delete_at;
}
