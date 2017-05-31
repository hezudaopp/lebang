package com.youmayon.lebang.data;

import java.util.List;

/**
 * Created by Jawinton on 17/05/04.
 */
public interface UserTaskDao {
    List<Object[]> taskAppReceivedAmount(long beginTime, long endTime);

    List<Object[]> taskAppCompletedAmount(long beginTime, long endTime);

    List<Object[]> taskAppAcceptedAmountAndTotalFlow(long beginTime, long endTime);

    List<Object[]> reviewerTaskAcceptedAmountAndTotalFlow(long beginTime, long endTime);

    List<Object[]> reviewerTaskReviewedAmount(long beginTime, long endTime);
}
