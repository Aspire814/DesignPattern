package com.storm.service.impl;

import com.storm.common.annotation.Component;
import com.storm.common.annotation.Transactional;
import com.storm.common.util.TransactionManager;
import com.storm.mapper.StormTestMapper;
import com.storm.model.StormTest;
import com.storm.service.ISingleService;

@Component("singleService")
public class SingleService implements ISingleService {

    @Override
    @Transactional
    public void doSomething() {
        //使用事务管理器来开启统一事务，不要用spring来注入mapper
        StormTestMapper mapper = (StormTestMapper) TransactionManager.getMpper(StormTestMapper.class);
        //模拟转账操作
        StormTest m1 = mapper.selectByPrimaryKey(1);
        StormTest m2 = mapper.selectByPrimaryKey(2);
        m1.setValue(m1.getValue() - 100);
        m2.setValue(m2.getValue() + 100);
        mapper.updateByPrimaryKey(m1);
        int zero = 0;
        setError(zero);
        mapper.updateByPrimaryKey(m2);
    }

    /**
     * 手动制造错误使运行出错
     */
    private void setError(int b) {
        int a = 1;
        int c = a / b;
    }
}
