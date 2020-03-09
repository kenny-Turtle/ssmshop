package com.zfj.service;

import com.zfj.pojo.Test;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/24 17:43
 */
public interface TestService {
    Test findTestById(int id);

    List<Test> findAll();
}
