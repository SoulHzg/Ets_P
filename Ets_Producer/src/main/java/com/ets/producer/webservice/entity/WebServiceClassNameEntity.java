package com.ets.producer.webservice.entity;

import com.alibaba.fastjson.JSONObject;
import com.ets.common.until.StringUntil;
import com.ets.producer.annotation.WebServiceBean;
import com.ets.producer.webservice.imp.InvokeServiceImp;
import lombok.Data;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

/**
 * 说明：扫描目标类包下所有包含WebServiceBean注解类
 *
 * 作者：hzg
 *
 * 时间：2019-04-02
 *
 * */
@Data
public class WebServiceClassNameEntity {

    /* 接口传参类型 */
    private final WebServiceParamTypeEnum webServiceParamTypeEnum = WebServiceParamTypeEnum.JSON;

    /* 目标类 */
    private final Class targetClass = InvokeServiceImp.class;

    /* 是否扫描子包 */
    private final boolean isScanChildPackage = true;

    /* 目标类包下所有类名集合 */
    private List<String> classNameList = null;

    /* WebServiceBean注解类值集合 */
    private HashSet<String> annotationSet = new HashSet<>();

    /* WebServiceBean注解Map  key：注解value值  value：注解class */
    private HashMap<String,Object> annotationMap = new HashMap<>();

    public WebServiceClassNameEntity(){
        init();
    }

    private void init(){
        String className = targetClass.getName();
        int pointIndex = className.lastIndexOf(".");
        String classPath = className.substring(0,pointIndex);
        this.getClassName(classPath);
        Iterator<String> classIt = classNameList.iterator();
        while(classIt.hasNext()){
            //获取全类名参数并封装
            this.loadClass(classIt.next());
        }
        System.out.println("WebServiceBean注解集合值："+JSONObject.toJSONString(annotationMap));
        //加载完成，释放参数
        this.clearParam();
    }

    /**
     * 说明：获取包下的所有类名
     *
     * 参数：
     * @param packageName:包路径
     *
     * */
    private void getClassName(String packageName){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".","/");
        URL url = classLoader.getResource(packagePath);
        if(url != null){
            String type = url.getProtocol();
            if("file".equals(type)){
                this.getClassNameByFile(url.getPath());
            }
        }
    }

    /**
     * 说明：从项目文件获取某包下所有类
     *
     * 参数：
     * @param filePath：文件路径
     *
     */
    private void getClassNameByFile(String filePath){
        if(classNameList == null)classNameList = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (isScanChildPackage) {//迭代
                    getClassNameByFile(childFile.getPath());
                }
            }else{
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
                            childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    classNameList.add(childFilePath);
                }
            }
        }
    }

    /**
     * 说明：根据全类名获取相应参数
     *
     * 参数：
     * @param classPath:全类名
     *
     *
     * */
    private void loadClass(String classPath){
        try {
            Class<?> classes = Class.forName(classPath);
            if(classes.isAnnotationPresent(WebServiceBean.class)){
                WebServiceBean webServiceBean = classes.getAnnotation(WebServiceBean.class);
                if(!StringUntil.hasContent(webServiceBean.value()))throw new RuntimeException("类["+classPath+"]WebServiceBean注解值不能空，请核实。");
                if(judgeContain(webServiceBean.value())){
                    annotationMap.put(webServiceBean.value(),classes);
                }else{//WebServiceBean注解值重复
                    throw new RuntimeException("类["+classPath+"]WebServiceBean注解值["+webServiceBean.value()+"]重复，请核实。");
                }
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 说明：判断当前注解值是否存在，不存在及保存
     *
     * 参数：
     * @param value：注解参数值
     *
     * */
    private boolean judgeContain(String value){
        return annotationSet.contains(value) == false ? annotationSet.add(value): false;
    }

    /**
     * 说明：加载完成释放参数集
     *
     * */
    private void clearParam(){
        classNameList.clear();
        annotationSet.clear();
    }


}
