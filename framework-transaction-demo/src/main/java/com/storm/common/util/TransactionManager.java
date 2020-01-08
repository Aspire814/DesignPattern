package com.storm.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TransactionManager {

    private static Map<Object, SqlSession> sessionMap = new HashMap<>();

    public static Object getMpper(Class<?> currentClass, Class<?> targetProxyClass) {
        SqlSessionFactory factory = null;
        try {
            factory = SpringUtil.getBean("sqlSessionFactory");
            SqlSession sqlSession = sessionMap.get(currentClass.getName());
            return sqlSession.getMapper(targetProxyClass);
        } catch (Exception e) {
            throw new Error("SqlSession 加载错误！");
        }
    }

    public Map<Object, SqlSession> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<Object, SqlSession> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
