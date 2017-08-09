package com.ruaho.studyapp.http;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.ruaho.studyapp.application.App;
import com.ruaho.studyapp.bean.JsonUtils;
import com.ruaho.studyapp.other.NetWorkThreadPool;
import com.ruaho.studyapp.other.ServiceContext;
import com.ruaho.studyapp.utils.NetUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ruaho on 2017/8/9.
 */

public class ShortConnection {

    private static final String LOG_TAG = "ShortConnection";

    /**
     * @param servId 服务ID
     * @param params 参数
     */
    public static void doAct(final String servId, final String act, final Map<Object, Object> params
            , final ShortConnHandler handler) {

        if (!beforeConnect(handler)) {
            return;
        }
        Log.i("haha", "我是http请求");
        NetWorkThreadPool.getInstance().execute(getRunnable(servId, act, params, handler));

    }

    @NonNull
    private static Runnable getRunnable(final String servId,
                                        final String act,
                                        final Map<Object, Object> params,
                                        final ShortConnHandler handler) {
        return new Runnable() {
            @Override
            public void run() {
                final long beginTime = System.currentTimeMillis();
                try {
                    HttpClientProvider provider = new HttpClientProvider();
                    String url = ServiceContext.getHttpServer() + servId + "." + act + ".do";
                    provider.createHttpClient();
                    provider.doPost(url, params, null, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(LOG_TAG, e.getMessage(), e);
                            OutBean outBean = new OutBean();
                            outBean.setError(e.getMessage());
                            handlerError(handler, outBean, beginTime);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                String content = response.body().string();
                                Log.i("ShortConnection", "CONTENT_LENGTH:" + content.length() + ";"
                                        + (System.currentTimeMillis() - beginTime));
                                if (response.isSuccessful()) {
                                    OutBean outBean = null;
                                    try {
                                        outBean = new OutBean(JsonUtils.toBean(content));

                                    } catch (Exception e) {
                                        outBean = new OutBean(JsonUtils.toBean(content));
                                        outBean.setError();
                                        handlerError(handler, outBean, beginTime);
                                        return;
                                    }
                                    if (outBean.isError()) {
                                        handlerError(handler, outBean, beginTime);
                                    } else {
                                        handlerSuccess(handler, outBean, beginTime);
                                    }
                                } else {
                                    OutBean outBean = new OutBean(JsonUtils.toBean(content));
                                    String errorDesc = content;
                                    if (TextUtils.isEmpty(errorDesc)) {
                                        errorDesc = getErrorDescByHttpCode(response.code());
                                    }
                                    if (!TextUtils.isEmpty(errorDesc)) {
                                        outBean.setError(errorDesc);
                                    } else {
                                        outBean.setError(content);
                                    }

                                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) { //401
                                        handlerError(handler, new OutBean(JsonUtils.toBean(content)), beginTime);
                                    } else {
                                        handlerError(handler, outBean, beginTime);
                                    }
                                }
                            } catch (Exception e) {
                                Log.e(LOG_TAG, e.getMessage(), e);
                                OutBean outBean = new OutBean();
                                outBean.setError(e.getMessage());
                                handlerError(handler, outBean, beginTime);
                            }

                        }
                    });
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                    OutBean outBean = new OutBean();
                    outBean.setError(e.getMessage());
                    handlerError(handler, outBean, beginTime);
                }
            }
        };
    }

    private static void handlerError(final ShortConnHandler handler, OutBean outBean, long beginTime) {
        final long endTime = System.currentTimeMillis();
        Log.d(LOG_TAG, "elapsed time：" + (endTime - beginTime) + "ms");
        if (handler != null) {
            handler.onError(outBean);
        }
    }

    private static void handlerSuccess(final ShortConnHandler handler, OutBean outBean, long beginTime) {
        final long endTime = System.currentTimeMillis();
        Log.i(LOG_TAG, "elapsed time：" + (endTime - beginTime) + "ms");
        if (handler != null) {
            handler.onSuccess(outBean);
        }
    }

    /**
     * 在远端处理之前的操作：如网络监视等;
     *
     * @return true为可向后执行，false为不可向后执行
     */
    public static boolean beforeConnect(ShortConnHandler handler) {
        String errorMsg = null;
        if (!NetUtil.isNetworkAvailable(App.getAppContext())) { //没有网络
            errorMsg = "网络连接异常，请连接网络后重试！";
        }

        if (TextUtils.isEmpty(errorMsg)) { //没有错误信息返回
            return true;
        } else {
            if (handler != null) {
                handler.onError(new OutBean().setError(errorMsg));
            }
            return false;
        }
    }

    /**
     * 根据对应的HTTP CODE 取得对应的描述信息
     */
    public static String getErrorDescByHttpCode(int httpCode) {
        switch (httpCode) {
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                return "服务无权限，请重新登录";
            case HttpURLConnection.HTTP_NOT_FOUND:
                return "服务没有找到，请联系管理员";
            case HttpURLConnection.HTTP_UNAVAILABLE:
                return "服务不可用，请稍后重试";
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                return "服务内部错误，请联系管理员";
            case HttpURLConnection.HTTP_BAD_GATEWAY:
                return "网关不可用，请稍后重试";
            case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                return "请求超时，请稍后重试";
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                return "网关超时，请稍后重试";
            default:
                return "";
        }
    }
}
