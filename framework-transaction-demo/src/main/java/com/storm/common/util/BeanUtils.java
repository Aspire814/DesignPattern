package com.storm.common.util;

import java.lang.annotation.ElementType;
import java.lang.reflect.GenericDeclaration;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import com.storm.common.annotation.Component;
import com.storm.common.annotation.Transactional;

public class BeanUtils {
    public static Set<? extends GenericDeclaration> scanAnnotationByPackageName(String packageName, ElementType type) {
        if (packageName == null || packageName.isEmpty()) {
            throw new Error("扫描的包名为空！");
        }
        if (type == null) {
            throw new Error("扫描的注解类型为空！");
        }
        if (type == ElementType.TYPE) {
            //用于类级别的注解
            return  new Reflections(packageName).getTypesAnnotatedWith(Component.class);
        }
        if (type == ElementType.METHOD) {
            return new Reflections(packageName, new MethodAnnotationsScanner())
                    .getMethodsAnnotatedWith(Transactional.class);
        }
        return new HashSet<>();
    }

}
