package cn.eqianyuan.util;

import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.util.yamlMapper.ClientConf;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 短信通道工具
 * Created by jason on 2016-07-11.
 */
public class SMSUtils {

    private static Logger logger = Logger.getLogger(SMSUtils.class);

    /**
     * 发送短信
     *
     * @param mobile  手机号码
     * @param message 消息内容
     * @return
     */
    public static boolean batchSend2(String mobile, String message) throws EqianyuanException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String batchSend2 = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.SMS.SMS.toString(), ClientConf.SMS.BatchSend2.toString());
        String corpID = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.SMS.SMS.toString(), ClientConf.SMS.CorpID.toString());
        String pwd = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.SMS.SMS.toString(), ClientConf.SMS.Pwd.toString());

        if (ObjectUtils.isEmpty(batchSend2)) {
            logger.warn("batchSend fail , because BatchSend2 not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        if (ObjectUtils.isEmpty(corpID)) {
            logger.warn("batchSend fail , because CorpID not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        if (ObjectUtils.isEmpty(pwd)) {
            logger.warn("batchSend fail , because Pwd not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        try {
            batchSend2 += "?CorpID=" + corpID
                    + "&Pwd=" + pwd
                    + "&Mobile=" + mobile
                    + "&Content=" + URLEncoder.encode(message, "GBK");
        } catch (UnsupportedEncodingException e) {
            logger.error("URLEncoder.encode(message, GBK) error", e);
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_SEND_ERROR);
        }

        HttpGet httpGet = new HttpGet(batchSend2);
        HttpResponse response;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            logger.error("httpClient.execute() error", e);
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_SEND_ERROR);
        }
        HttpEntity entity = response.getEntity();
        String responseContent;
        try {
            responseContent = EntityUtils.toString(entity);
        } catch (IOException e) {
            logger.error("EntityUtils.toString() error", e);
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_SEND_ERROR);
        }
        if (Long.parseLong(responseContent) > 0) {
            return true;
        }

        logger.error("SMS batchSend2 error, error code is [" + responseContent + "]");
        throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_SEND_ERROR);
    }
}
