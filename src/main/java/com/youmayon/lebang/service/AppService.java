package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.App;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * app渠道service类
 * Created by Jawinton on 17/05/02.
 */
public interface AppService {
    /**
     * 获取app详情
     * @param id
     * @return
     */
    App findOne(long id);

    /**
     * 保存app
     * @param app
     * @return
     */
    App save(App app);

    /**
     * 分页获取app列表
     * @param page
     * @param size
     * @return
     */
    Page<App> list(int page, int size);

    /**
     * 获取app列表
     * @return
     */
    List<App> list(boolean enabled);

    /**
     * 获取app详情
     * @param name
     * @return
     */
    App findOne(String name);
}
