package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TokenLogoutRequestModel {
    private UUID member_id;
}
