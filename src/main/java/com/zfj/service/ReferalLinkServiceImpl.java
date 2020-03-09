package com.zfj.service;

import com.zfj.mapper.ReferalLinkMapper;
import com.zfj.pojo.ReferalLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/28 15:35
 */
@Service
public class ReferalLinkServiceImpl implements ReferalLinkService {

    @Autowired
    ReferalLinkMapper referalLinkMapper;

    public List<ReferalLink> listAll() {
        return referalLinkMapper.selectByExample();
    }

    public void update(ReferalLink link) {
        referalLinkMapper.updateByPrimaryKey(link);
    }

    //更新链接显示的文本
    public void updateLinkText(Integer id, String text) {
        ReferalLink link = get(id);
        link.setText(text);
        update(link);
    }

    //更新链接的链接地址
    public void updateLinkLink(Integer id, String link) {
        ReferalLink referalLink = get(id);
        referalLink.setLink(link);
        update(referalLink);
    }

    public ReferalLink get(Integer id) {
        return referalLinkMapper.selectByPrimaryKey(id);
    }
}
