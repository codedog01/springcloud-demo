package com.lengao.springclouddemo.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/15
 */
@Configuration
public class WeChatPayConfig {
    @Value("${wechat-pay.merchant-id}")
    private String merchantId;
    @Value("${wechat-pay.merchant-serial-number}")
    private String merchantSerialNumber;
    @Value("${wechat-pay.private-key}")
    private String privateKey;
    @Value("${wechat-pay.api-v3-key}")
    private String apiV3Key;
    /**
     * 给容器中加入WechatPay的HttpClient,虽然它是WechatPay的,
     * 但可以用它给任何外部发请求,因为它只对发给WechatPay的请求做处理而不对发给别的的请求做处理.
     */
    @Bean
    public HttpClient httpClient(){
        //私钥
        PrivateKey merchantPrivateKey= PemUtil.loadPrivateKey(privateKey);
        //微信证书校验器
        Verifier verifier=null;
        try{
            //获取证书管理器实例
            CertificatesManager certificatesManager=CertificatesManager.getInstance();
            //向证书管理器增加需要自动更新平台证书的商户信息(默认时间间隔:24小时)
            certificatesManager.putMerchant(merchantId,new WechatPay2Credentials(merchantId,new PrivateKeySigner(merchantSerialNumber,merchantPrivateKey)),apiV3Key.getBytes(StandardCharsets.UTF_8));
            //从证书管理器中获取verifier
            verifier=certificatesManager.getVerifier(merchantId);
        }
        catch(Exception e){
            new RuntimeException("微信证书校验器配置失败");
        }
        WechatPayHttpClientBuilder builder=WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId,merchantSerialNumber,merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier));
        CloseableHttpClient httpClient=builder.build();
        return httpClient;
    }
    /**
     * 和上面相比只是不需要验证签名了
     * @return
     */
    @Bean
    public HttpClient httpClientWithNoSign(){
        //私钥
        PrivateKey merchantPrivateKey=PemUtil.loadPrivateKey(privateKey);
        //微信证书校验器
        Verifier verifier=null;
        try{
            //获取证书管理器实例
            CertificatesManager certificatesManager=CertificatesManager.getInstance();
            //向证书管理器增加需要自动更新平台证书的商户信息(默认时间间隔:24小时)
            certificatesManager.putMerchant(merchantId,new WechatPay2Credentials(merchantId,new PrivateKeySigner(merchantSerialNumber,merchantPrivateKey)),apiV3Key.getBytes(StandardCharsets.UTF_8));
            //从证书管理器中获取verifier
            verifier=certificatesManager.getVerifier(merchantId);
        }
        catch(Exception e){
            new RuntimeException("微信证书校验器配置失败");
        }
        WechatPayHttpClientBuilder builder=WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId,merchantSerialNumber,merchantPrivateKey)
                .withValidator(response->true);
        CloseableHttpClient httpClient=builder.build();
        return httpClient;
    }
}
