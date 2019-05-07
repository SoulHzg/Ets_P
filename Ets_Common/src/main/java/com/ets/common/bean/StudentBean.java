package com.ets.common.bean;

import lombok.Data;

import java.util.List;

@Data
public class StudentBean {
    private int id;
    private String name;
    private int sex;
    private int age;
    private List<BookBean> bookBeanList;
}
