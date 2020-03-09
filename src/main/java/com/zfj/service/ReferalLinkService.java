package com.zfj.service;

import com.zfj.pojo.ReferalLink;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/28 15:35
 */
public interface ReferalLinkService {
    /**
     * 返回所有的ReferalLink
     *
     * @return
     */
    List<ReferalLink> listAll();

    /**
     * 更新
     *
     * @param link
     */
    void update(ReferalLink link);

    /**
     * 更新链接显示的文本
     *
     * @param id
     * @param text
     */
    void updateLinkText(Integer id, String text);

    /**
     * 更新链接的链接地址
     *
     * @param id
     * @param link
     */
    void updateLinkLink(Integer id, String link);

    /**
     * 根据id返回一个ReferalLink对象
     *
     * @param id
     * @return
     */
    ReferalLink get(Integer id);
}
