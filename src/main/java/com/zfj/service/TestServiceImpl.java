package com.zfj.service;

import com.zfj.mapper.TestMapper;
import com.zfj.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/24 17:44
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    public Test findTestById(int id) {
        return testMapper.selectByPrimaryKey(id);
    }

    public List<Test> findAll() {
        return testMapper.selectByExample();
    }
}
