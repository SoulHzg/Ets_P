package com.ets.producer.webservice.imp;


import com.alibaba.fastjson.JSONObject;
import com.ets.producer.webservice.entity.WebServiceClassNameEntity;
import com.ets.producer.webservice.InvokService;
import com.ets.producer.webservice.until.ConstantUntil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InvokeServiceImp implements InvokService {

    @Autowired
    private WebServiceClassNameEntity classNameEnetity;

    @Override
    public String invoke(String param) {
        String result = "";
        switch (classNameEnetity.getWebServiceParamTypeEnum()){
            case JSON:
                result = invokeJson(JSONObject.parseObject(param));
                break;
            case XML:

                break;
        }
        return result;
    }

    private String invokeJson(JSONObject jsonObject){
        String result = "";
        try {
            //自定义WebServiceBean注解值
            String classValue = (String)jsonObject.get(ConstantUntil.classValue);
            //目标方法名
            String classMethod = (String)jsonObject.get(ConstantUntil.classMethod);
            HashMap<String,Object> annotationMap = classNameEnetity.getAnnotationMap();
            Class clazz = (Class)annotationMap.get(classValue);
            Method method = clazz.getMethod(classMethod,String.class);
            result = (String)method.invoke(clazz.newInstance(),JSONObject.toJSON(jsonObject));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }





}
