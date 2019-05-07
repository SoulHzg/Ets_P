package com.ets.producer.services;

import com.ets.common.bean.StudentBean;
import com.ets.producer.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public void addStudent(StudentBean studentBean){
        studentMapper.addStudent(studentBean);
    }

    public List<StudentBean> qryStudentList(){
        return studentMapper.qryStudentList();
    }

    public StudentBean qryStudentById(int id){
        return studentMapper.qryStudentById(id);
    }
}
