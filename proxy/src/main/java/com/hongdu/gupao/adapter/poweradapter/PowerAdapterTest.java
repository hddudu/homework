package com.hongdu.gupao.adapter.poweradapter;

/**
 * @ClassName PowerAdapterTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/20 20:02
 * @Version 1.0
 */
public class PowerAdapterTest {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }
}
