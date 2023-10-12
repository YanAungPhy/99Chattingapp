package com.chatapp.nineninechatapp.Utils.Camera;

public interface QrCodeCallback<T> {

    /**
     * 完成
     *
     * @param success 状态
     * @param ret     结果
     */
    void onComplete(boolean success, T ret);

}
