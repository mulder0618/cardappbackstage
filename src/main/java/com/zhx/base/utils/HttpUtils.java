package com.zhx.base.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    private static Logger log = Logger.getLogger(HttpUtils.class.toString());

    /**
     * https  post请求
     *
     * @param url
     * @param map
     * @return
     */
    public static String doPost(String url, Map map, Object headp) {
        HttpPost httpPost = null;
        String result = null;
        try {
            httpPost = new HttpPost(url);
            // 设置Cookie
            if (headp != null) {
                Header[] header = (Header[]) headp;
                for (Header he : header) {
                    if ("Set-Cookie".equals(he.getName())) {
                        httpPost.addHeader(he);
                    }
                }
            }
            //httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
                Object key = it.next();
                list.add(new BasicNameValuePair(String.valueOf(key), String.valueOf(map.get(key))));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(entity);
            }
            HttpResponse response = BaseHttpClient.getHttpclient().execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * http  get请求
     *
     * @param url
     * @param headp
     * @return
     */
    public static String doGet(String url, Object headp) {
        HttpGet httpget = null;
        String result = null;
        try {
            httpget = new HttpGet(url);
            // 设置Cookie
            if (headp != null) {
                Header[] header11 = (Header[]) headp;
                for (Header he : header11) {
                    if ("Set-Cookie".equals(he.getName())) {
                        httpget.addHeader(he);
                    }
                }
            }
            //httpget.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
            HttpResponse response = BaseHttpClient.getHttpclient().execute(httpget);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpget.releaseConnection();
        }
        return result;
    }


    public static void main(String[] args) {

    }


}
