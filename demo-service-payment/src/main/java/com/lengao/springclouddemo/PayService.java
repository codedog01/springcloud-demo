package com.lengao.springclouddemo;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/15
 */
public class PayService {
    private String createPay(OrderInfo orderInfo) throws Exception{
        //请求构造
        HttpPost httpPost=new HttpPost(domain+WechatPayConstant.CREATE_PAY_URL);
        //请求体
        //构造数据
        HashMap<String,Object> reqData=new HashMap<>();
        reqData.put("appid",appId);
        reqData.put("mchid",merchantId);
        reqData.put("description",orderInfo.getTitle());
        reqData.put("out_trade_no",orderInfo.getOrderNo());
        reqData.put("notify_url",notifyUrl+"/pay/order/order-signal");
        HashMap<String,Integer> amount=new HashMap<>();
        //单位是分
        amount.put("total",orderInfo.getTotalFee());
        reqData.put("amount",amount);
        String jsonReqData=new Gson().toJson(reqData);
        StringEntity entity=new StringEntity(jsonReqData,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        //请求头
        httpPost.setHeader("Accept","application/json");
        //完成签名并执行请求
        CloseableHttpResponse response=(CloseableHttpResponse)httpClient.execute(httpPost);
        Map<String,String> dataMap=null;
        try{
            int statusCode=response.getStatusLine()
                    .getStatusCode();
            //成功
            if(statusCode==200){
                String body=EntityUtils.toString(response.getEntity());
                dataMap=new Gson().fromJson(body,HashMap.class);
            }
            //失败
            else{
                if(statusCode!=204){
                    String body=EntityUtils.toString(response.getEntity());
                    log.error(body);
                    return null;
                }
            }
        }
        finally{
            response.close();
        }
        //返回二维码的地址
        return dataMap.get("code_url");
    }
}
