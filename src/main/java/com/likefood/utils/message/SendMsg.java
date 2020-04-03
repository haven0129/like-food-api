package com.likefood.utils.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class SendMsg {
    private static final Logger logger = LoggerFactory.getLogger(SendMsg.class);

    @Value("${msg.apikey}")
    private String apikey;
    @Value("${msg.tplId}")
    private long tplId;
    /**
     * 发送短信验证码
     * @param mobile
     */
    public String sentValidCode(String mobile)throws Exception{
        String code = RandomNumberUtils.get6NumberStringRandom();
        String param = URLEncoder.encode("#code#", "UTF-8") + "="
                + URLEncoder.encode(code + " ", "UTF-8");
        String sentRe = JavaSmsApi.tplSendSms(apikey, tplId, param, mobile);
        logger.info("获得短信验证码：" + sentRe);
        return code;
    }
    /*public static void main(String [] args)throws Exception{
        sentValidCode("138xxxxxxxxx");
    }*/
}
