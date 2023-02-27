package com.lengao.springcloud.demogoods;

import feign.RetryableException;
import feign.Retryer;

/**
 * @author LengAo
 * @date 2023/2/27
 */
public class CustomRetryer implements Retryer {
    @Override
    public void continueOrPropagate(RetryableException e) {

    }
}
