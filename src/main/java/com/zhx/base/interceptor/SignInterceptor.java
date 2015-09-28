package com.zhx.base.interceptor;

import com.zhx.base.utils.SignUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by mulder on 2015/6/11.
 */
public class SignInterceptor implements HandlerInterceptor {

    private Logger log = Logger.getLogger(String.valueOf(SignInterceptor.class));

    public SignInterceptor() {
        // TODO Auto-generated constructor stub
    }

    private String mappingURL;//利用正则映射到需要拦截的路径

    public void setMappingURL(String mappingURL) {
        this.mappingURL = mappingURL;
    }


    /**
     * 验签拦截器 组装接收的参数并判断签名
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler)
            throws Exception
    {
        String sign = "";
        Map paramMap =  request.getParameterMap();
        if(paramMap.size()==0){
            //throw new SignException("基本参数未传");
            return true;
        }
        Iterator<Map.Entry<String, String[]>> entries = paramMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String[]> entry = entries.next();
            String key = entry.getKey();
            if(key.equals("sign")){
                for (String val : entry.getValue() ) {
                    sign = val;
                }
            }
        }
        if("".equals(sign)){
            return true;
            //throw new SignException("sign参数未传");
        }
        String serverSign =  SignUtil.buildServerSign(paramMap);
        log.info("sign: "+sign);
        log.info("webSign: "+ SignUtil.buildServerSign(paramMap) );
        if(serverSign.equals(sign)){
            return true;
        }
        else{
            return true;
            //throw new SignException("签名不一致");
        }

    }

    //在业务处理器处理请求执行完成后,生成视图之前执行的动作
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView)
            throws Exception
    {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * <p/>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }

}
