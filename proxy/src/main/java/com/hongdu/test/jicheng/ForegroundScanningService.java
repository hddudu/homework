package com.hongdu.test.jicheng;

/**
 * @ClassName ForegroundScanningService
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 15:03
 * @Version 1.0
 */
public class ForegroundScanningService extends BaseProcessService {
    @Override
    public Response submitTask(BaseProcessRequest condition) {
        ForegoundScanningRequest request = (ForegoundScanningRequest)condition;
        System.out.println(request);
        return super.submitTask(condition);
    }
}
