package com.hongdu.test.jicheng;

/**
 * @ClassName Main
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 15:04
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ForegoundScanningRequest foregoundScanningRequest = new ForegoundScanningRequest();
        foregoundScanningRequest.setDocId("docId");
        foregoundScanningRequest.setDocDt("docDt");

        ForegroundScanningService foregroundScanningService = new ForegroundScanningService();
        foregroundScanningService.submitTask(foregoundScanningRequest);

    }
}
