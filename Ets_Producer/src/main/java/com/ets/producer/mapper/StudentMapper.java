package com.ets.producer.mapper;

import com.ets.common.bean.StudentBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface StudentMapper {
    public void addStudent(StudentBean studentBean);

    public List<StudentBean> qryStudentList();

    public StudentBean qryStudentById(int id);
}
