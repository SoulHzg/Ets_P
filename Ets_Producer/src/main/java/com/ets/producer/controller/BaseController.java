package com.ets.producer.controller;

import com.alibaba.fastjson.JSONObject;
import com.ets.common.bean.StudentBean;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;


public class BaseController {

    protected enum ReturnType{
        JSONOBJECT,STRING
    }

    /**
     * 说明：封装JsonObject、JsonString数据类型
     *
     * 参数：
     * @param o：参数对象
     * @param r: 参数类型
     * @param tClass : 参数类型class
     *
     * */
    protected <T> T encapsulationParamToJsonObjectOrString(@NotNull Object o, @NotNull ReturnType r,@NotNull Class<T> tClass){
        return encapsulationParam_(o,null,r,tClass);
    }

    /**
     * 说明：封装ModelAndView数据类型
     *
     * 参数：
     * @param map：参数类型为map的对象
     * @param viewName: 视图名称
     *
     * */
    protected ModelAndView encapsulationMapToMv(@NotNull Map<String,Object> map,String viewName){
        return returnModuleAndView(map,viewName);
    }

    /**
     * 说明：封装ModelAndView数据类型
     *
     * 参数：
     * @param o：参数类型为javaBean的对象
     * @param viewName: 视图名称
     *
     * */
    protected ModelAndView encapsulationJavaBeanToMv(@NotNull Object o,String viewName){
        return returnModuleAndView(o,viewName);
    }

    private <T> T encapsulationParam_(Object o,String viewName,ReturnType r,Class<T> tClass){
        T result = null;
        try {
            result = tClass.newInstance();
            switch (r){
                case JSONOBJECT:
                    result = (T)returnJSONObject(o);
                    break;
                case STRING:
                    result = (T)returnString(o);
                    break;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private JSONObject returnJSONObject(Object o){
        return JSONObject.parseObject(JSONObject.toJSONString(o));
    }

    private String returnString(Object o){
        return JSONObject.toJSONString(o);
    }

    private ModelAndView returnModuleAndView(Object o,String viewName){
        if(o == null) return null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        if(o instanceof Map){
            //map数据解析放入ModelAndView中
            modelAndView = analysisParam(o,modelAndView);
        }else{
            //将javaBean对象转换为map，再将map数据解析放入ModelAndView中
            modelAndView = analysisParam(convertJavaBeanToMap(o),modelAndView);
        }
        return modelAndView;
    }

    //将map数据解析放入ModelAndView中
    private ModelAndView analysisParam(Object o,ModelAndView mv){
        if(o == null) return null;
        Map<String,Objects> map = (Map<String,Objects>)o;
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            mv.addObject((String)entry.getKey(),entry.getValue());
        }
        return mv;
    }

    //将javaBean数据转换为Map
    private Map<String,Object> convertJavaBeanToMap(Object o){
        if (o == null) return null;
        Map<String,Object> map = new HashMap<String,Object>();
        Field [] fields = o.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            try {
                Field field = o.getClass().getDeclaredField(fields[i].getName());
                field.setAccessible(true);
                Object v = field.get(o);
                map.put(fields[i].getName(),v);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void main(String[] args) {
        StudentBean studentBean = new StudentBean();
        studentBean.setName("胡志刚");
        studentBean.setAge(27);
        studentBean.setSex(0);
        ModelAndView mv = new BaseController().encapsulationJavaBeanToMv(studentBean,"hzg");
        System.out.println(mv);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("1","胡志刚");
        ModelAndView mv1 = new BaseController().encapsulationMapToMv(map,"hzg");
        System.out.println(mv1);
    }
}
