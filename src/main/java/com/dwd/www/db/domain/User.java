package com.dwd.www.db.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String userName;

    private String password;

    private String age;

    private String sex;

    private String permission;

    private String isDelete;

}