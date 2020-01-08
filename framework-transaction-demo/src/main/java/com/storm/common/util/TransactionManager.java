package com.storm.common.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.storm.mapper.StormTestMapper;

public class TransactionManager {
    public static Object getMpper(Class<?> clazz) {
        SqlSessionFactory factory = null;
        try {
            factory = SpringUtil.getBean("sqlSessionFactory");
            SqlSession sqlSession = factory.openSession(false);
            return sqlSession.getMapper(clazz);
        } catch (Exception e) {
            throw new Error("SqlSession 加载错误！");
        }
    }

}
