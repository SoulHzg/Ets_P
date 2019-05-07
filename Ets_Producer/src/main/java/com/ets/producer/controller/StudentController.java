package com.ets.producer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ets.common.bean.StudentBean;
import com.ets.producer.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student/addStudent")
    public JSONObject addStudent(){
        StudentBean studentBean = new StudentBean();
        studentBean.setName("胡志刚");
        studentBean.setAge(27);
        studentBean.setSex(0);
        studentService.addStudent(studentBean);
        return encapsulationParamToJsonObjectOrString(studentBean,ReturnType.JSONOBJECT,JSONObject.class);
    }

    public List<StudentBean> qryStudentList(){
        return studentService.qryStudentList();
    }

    @RequestMapping("/student/qryStudentById/{id}")
    public StudentBean qryStudentById(@PathVariable int id){
        return studentService.qryStudentById(id);
    }
}
