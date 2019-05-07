package com.ets.common.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserBean implements Serializable {

    private String id;

    private String name;

    private String age;
}
