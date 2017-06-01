package com.youmayon.lebang.data;

import java.util.List;

/**
 * Created by Jawinton on 17/05/04.
 */
public interface TaskDao {
    /**
     * 获取任务总数量，第一个代表总数量，第二个代表剩余未领取总数量，第三个代表完成总数量，第四个代表审核通过总数量，第五个代表审核拒绝总数量
     * @return
     */
    List<Object[]> totalAmountObjectArray();
}
