package com.zhx.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mulder on 2015/9/26.
 */
public class ParamsUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 封装返回结果JSON  并生成验签
     * @param data
     * @return
     */
    public static Map packageSuccessReturnParamas(Object data) {
        String datastring = "";
        try {
            datastring = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("result", "000");
        map.put("msg", "成功");
        map.put("data", datastring);
        map.put("userId", "1000001");
        map.put("queryid", "asdsadasfdqwqw0111");
        String sign = SignUtil.bulidSign(map);
        map.put("sign", sign);
        return map;
    }


    /**
     * 封装错误状态码
     * @param result
     * @param msg
     * @return
     */
    public static Map packageErrorReturnParamas(String result,String msg) {
        Map map = new HashMap();
        map.put("result", result);
        map.put("msg", msg);
        map.put("userId", "1000001");
        map.put("queryid", "asdsadasfdqwqw0111");
        String sign = SignUtil.bulidSign(map);
        map.put("sign", sign);
        return map;
    }

}
