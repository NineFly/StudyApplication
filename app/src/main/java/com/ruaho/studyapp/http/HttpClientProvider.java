package com.ruaho.studyapp.http;

import android.util.Log;

import com.ruaho.studyapp.bean.JsonUtils;
import com.ruaho.studyapp.other.ServiceContext;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by jinyunyang on 15/3/4.
 */
public class HttpClientProvider {
    /**
     * 服务器端根据本参数的值，决定返回给客户端的数据格式。例如EaseChat1.0版不返回Base64格式的图片数据。
     */
    public static final String VER = "clientVersion";
    public static final String VER_VAL = "EaseChat1.0";

    /**
     * 发送POST请求时，需要放在URL上的参数KEY。
     */
    public static final String URL_PARAM = "_URL_PARAM";

    private static final String TAG = "OkHttpClientProvider";

    private static OkHttpClient httpClient;


    /**
     * 合并用于URL上的参数
     *
     * @param params 参数
     * @return 合并后的结果
     */
    private static String mergeUrlParam(Map<Object, Object> params, boolean addDefaultParam) {
        StringBuilder paramStr = new StringBuilder();
        if (params == null) {
            params = new HashMap();
        }

        if (addDefaultParam) {
            addDefaultParam(params);

        }
        Iterator iter1 = params.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry entry = (Map.Entry) iter1.next();
            Object key = entry.getKey();
            try {
                String val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
                paramStr.append("&").append(key).append("=").append(val);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }

        return paramStr.toString();
    }

    private static void addDefaultParam(Map<Object, Object> params) {
        // 增加版本参数
        params.put(VER, VER_VAL);
    }

    public static String createUrl(String url, Map<Object, Object> params, boolean addDefaultParam) {
        String paramStr = mergeUrlParam(params, addDefaultParam);
        if (paramStr.length() > 0) {
            paramStr = paramStr.replaceFirst("&", "?");
            url += paramStr;
        }

        return url;
    }

    /**
     * @param url    请求的URL
     * @param params 请求的参数
     * @return 服务器端返回的结果
     */
    public void doGet(String url, Map<Object, Object> params, Callback em) {
        /* 建立HTTPGet对象 */
        url = createUrl(url, params, true);

        Log.d(this.getClass().getSimpleName(), "url=" + url);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        addSystemRequestHeader(requestBuilder);

        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = httpClient.newCall(request);
        mcall.enqueue(em);
    }

    /**
     * 增加Http请求的Header数据
     */
    private void addSystemRequestHeader(Request.Builder builder) {
        HashMap map = new HashMap();
        map.put("X-DEVICE-NAME", ServiceContext.getUUID());
        // TODO: 2017/8/9 设置token
//        String token = DemoHXSDKHelper.getInstance().getToken();
//        if (StringUtils.isNotEmpty(token)) {
//            map.put("X-XSRF-TOKEN", token);
//        }
        appendHeaders(builder, map);
    }

    protected void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    /**
     * @param url    请求URL
     * @param params post参数
     * @return 服务器端返回的结果
     */
    public void doPost(String url, Map<Object, Object> params
            , Map<String, String> headers, Callback em) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (params.containsKey(URL_PARAM)) {
            url = createUrl(url, (Map<Object, Object>) params.get(URL_PARAM), false);
            params.remove(URL_PARAM);
        }
        Request.Builder builder = new Request.Builder()
                .url(url);
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                        JsonUtils.toJson(params));
        builder.post(body);
        addSystemRequestHeader(builder);
        appendHeaders(builder,headers);
        Request rr = builder.build();
        Call call = httpClient.newCall(rr);
        call.enqueue(em);
    }

    /**
     * @param url    请求URL
     * @param params post参数
     * @return 服务器端返回的结果
     */
    public Response doPostSync(String url, Map<Object, Object> params
            , Map<String, String> headers) throws IOException {
        if (params == null) {
            params = new HashMap<>();
        }
        if (params.containsKey(URL_PARAM)) {
            url = createUrl(url, (Map<Object, Object>) params.get(URL_PARAM), false);
            params.remove(URL_PARAM);
        }
        Request.Builder builder = new Request.Builder()
                .url(url);
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                        JsonUtils.toJson(params));
        builder.post(body);
        addSystemRequestHeader(builder);
        appendHeaders(builder,headers);
        Request rr = builder.build();
        return httpClient.newCall(rr).execute();
    }

    public void doBigDataPost(String url, Map<Object, Object> params, Callback em) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (params.containsKey(URL_PARAM)) {
            url = createUrl(url, (Map<Object, Object>) params.get(URL_PARAM), false);
            params.remove(URL_PARAM);
        }

        RequestBody body =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                        JsonUtils.toJson(params));
        Request.Builder builder = new Request.Builder()
                .url(url);
        addSystemRequestHeader(builder);

        Request rr = builder.post(body)
                .build();
        Call call = httpClient.newCall(rr);
        call.enqueue(em);
    }

    /**
     * 创建OkHttpClient对象
     *
     * @return 返回OkHttpClient对象
     */
    public OkHttpClient createHttpClient() {
        if (httpClient != null) {
            return httpClient;
        }
        synchronized (TAG) {
            if (httpClient == null) {
//                File sdcache = StorageUtils.getCacheDirectory(EChatApp.applicationContext);
//                int cacheSize = 10 * 1024 * 1024;
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .cache(null);
                httpClient = builder.build();
                return httpClient;
            }
            return httpClient;
        }
    }


}
