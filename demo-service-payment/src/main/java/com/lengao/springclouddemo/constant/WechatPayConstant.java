package com.lengao.springclouddemo.constant;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/15
 */
public class WechatPayConstant{
    public static final String CANCEL_PAY_URL="/v3/pay/transactions/out-trade-no/%s/close";
    public static final String CREATE_PAY_URL="/v3/pay/transactions/native";
    public static final String QUERY_PAY_URL="/v3/pay/transactions/out-trade-no/%s?mchid=%s";
    public static final String CREATE_REFUND_URL="/v3/refund/domestic/refunds";
    public static final String QUERY_REFUND_URL="/v3/refund/domestic/refunds/%s";
    public static final String TRADE_BILL_URL="/v3/bill/tradebill?bill_date=%s&bill_type=%s";
    public static final String FLOW_BILL_URL="/v3/bill/fundflowbill?bill_date=%s";
    public static final String TRADE_STATE_SUCCESS="SUCCESS";
    public static final String REFUND_STATE_SUCCESS="SUCCESS";
}
