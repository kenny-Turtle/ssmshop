package com.zfj.mapper;

import com.zfj.pojo.ReferalLink;
import com.zfj.pojo.ReferalLinkExample;
import java.util.List;

public interface ReferalLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReferalLink record);

    List<ReferalLink> selectByExample();

    ReferalLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ReferalLink record);
}