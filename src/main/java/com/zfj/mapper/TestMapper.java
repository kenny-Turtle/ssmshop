package com.zfj.mapper;

import com.zfj.pojo.Test;
import com.zfj.pojo.TestExample;
import java.util.List;

public interface TestMapper {
    int insert(Test test);

    List<Test> selectByExample();

    Test selectByPrimaryKey(int id);


/*
    int deleteByPrimaryKey(Long id);

    int insertSelective(Test record);

    List<Test> selectByExample(TestExample example);

    Test selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
    */
}