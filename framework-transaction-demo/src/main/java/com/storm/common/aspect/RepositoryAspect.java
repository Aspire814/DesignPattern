package com.storm.common.aspect;

import java.lang.reflect.Method;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepositoryAspect implements Aspect {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryAspect.class);

    private final SqlSession session;

    public RepositoryAspect(SqlSession session) {
        this.session = session;
    }

    @Override
    public void before(Object target, Method method, Object[] args) {
        logger.info("before doSomething...");
    }

    @Override
    public void after(Object target, Method method, Object[] args) {
        try {
            session.commit();
        } catch (Exception e) {
            logger.error("doSomething事务提交失败！");
        }
        logger.info("after doSomething...");
    }

    @Override
    public void afterException(Object target, Method method, Object[] args) {
        try {
            session.rollback();
        } catch (Exception e) {
            logger.error("doSomething事务回滚失败！");
        } finally {
            session.close();
        }
        logger.info("afterException doSomething...");
    }

}
