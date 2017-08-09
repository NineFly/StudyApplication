package com.ruaho.studyapp.http;


/**
 *
 * Created by jinyunyang on 15/3/6.
 */
public interface ShortConnHandler {
    /**
     * 执行成功的回调方法
     * @param outBean
     */
    public void onSuccess(OutBean outBean);

    /**
     * 执行失败的回调方法
     * @param outBean
     */
    public void onError(OutBean outBean);
}
