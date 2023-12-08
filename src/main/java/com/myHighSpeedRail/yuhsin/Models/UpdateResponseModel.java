package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UpdateResponseModel {
    private UUID member_id;
    private String member_name;
    private String member_password;
    private String member_email;
    private String member_phone;
}
