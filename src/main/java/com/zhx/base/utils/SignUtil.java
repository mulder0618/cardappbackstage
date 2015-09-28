package com.zhx.base.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by mulder on 2015/9/24.
 */
public class SignUtil implements Comparable<SignUtil> {

    private static Logger log = Logger.getLogger(String.valueOf(SignUtil.class));

    public String s;

    public SignUtil(String s) {
        this.s = s;
    }

    /**
     * ���ط����ǩ��
     * @param paramMap
     * @return
     */
    public static String buildServerSign(Map paramMap){
        Iterator<Map.Entry<String, String[]>> entries = paramMap.entrySet().iterator();
        String ss[] = new String[paramMap.size()-1];
        int index = 0;
        while (entries.hasNext()) {
            Map.Entry<String, String[]> entry = entries.next();
            String key = entry.getKey();
            if ("sign".equals(key)){
                continue;
            }
            else{
                ss[index] = key;
                index++;
            }
        }
        SignUtil mySs[]=new SignUtil[ss.length];//�����Զ������������
        for (int i = 0; i < ss.length; i++) {
            mySs[i]=new SignUtil(ss[i]);
        }
        StringBuffer paramBuffer = new StringBuffer();
        Arrays.sort(mySs);//����
        for (int i = 0; i < mySs.length; i++) {
            String key = mySs[i].s;
            paramBuffer.append(key);
            paramBuffer.append("=");
            try{
                String[] val = (String[]) paramMap.get(key);
                paramBuffer.append(val[0]);
            }
            catch (Exception e){;
                paramBuffer.append(paramMap.get(key));
            }
            paramBuffer.append("&");
        }
        String salt = PropertiesUtil.getValue("salt");
        String paramSha1 = Sha1Util.encodeSha1(paramBuffer.toString().substring(0, paramBuffer.length() - 1), salt);
        log.info("���յ��Ĳ���: " + paramBuffer.toString().substring(0, paramBuffer.length() - 1));
        log.info("sha1���: "+paramSha1);
        return paramSha1;
    }

    /**
     * ��ascii������ǩ���ֶ�
     * @param paramMap
     */
    public static String bulidSign(Map paramMap){
        Iterator<Map.Entry<String, String[]>> entries = paramMap.entrySet().iterator();
        String ss[] = new String[paramMap.size()];
        int index = 0;
        while (entries.hasNext()) {
            Map.Entry<String, String[]> entry = entries.next();
            String key = entry.getKey();
            ss[index] = key;
            index++;
        }
        SignUtil mySs[]=new SignUtil[ss.length];//�����Զ������������
        for (int i = 0; i < ss.length; i++) {
            mySs[i]=new SignUtil(ss[i]);
        }
        StringBuffer paramBuffer = new StringBuffer();
        Arrays.sort(mySs);//����
        for (int i = 0; i < mySs.length; i++) {
            String key = mySs[i].s;
            paramBuffer.append(key);
            paramBuffer.append("=");
            try{
                String[] val = (String[]) paramMap.get(key);
                paramBuffer.append(val[0]);
            }
            catch (Exception e){;
                paramBuffer.append(paramMap.get(key));
            }
            paramBuffer.append("&");
        }
        String salt = PropertiesUtil.getValue("salt");
        String paramSha1 = Sha1Util.encodeSha1(paramBuffer.toString().substring(0, paramBuffer.length() - 1), salt);
        log.info("���յ��Ĳ���: " + paramBuffer.toString().substring(0, paramBuffer.length() - 1));
        log.info("sha1���: "+paramSha1);
        return paramSha1;
    }


    @Override
    public int compareTo(SignUtil o) {
        if (o == null || o.s == null) return 1;
        if (s.length() > o.s.length()) return 1;
        else if (s.length() < o.s.length()) return -1;
        return s.compareTo(o.s);
    }



    public  static  void main(String[] args){
        //�������ɿͻ�������ǩ��
        Map map = new HashMap();
        //map.put("cardNo", "731001123");
        //map.put("cardPassword", "123123");
        map.put("os", "ios");
        SignUtil.bulidSign(map);

        //�������ɷ������֤ǩ��
        /*Map serverMap = new HashMap();
        map.put("param3","3");
        map.put("param1","1");
        map.put("param2",2);
        map.put("sign","sfsfsfsf");
        SignUtil.buildServerSign(map);*/
    }
}