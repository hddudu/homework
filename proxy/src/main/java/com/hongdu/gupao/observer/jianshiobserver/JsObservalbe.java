package com.hongdu.gupao.observer.jianshiobserver;

/**
 * @ClassName JsObservalbe
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 9:07
 * @Version 1.0
 */
public interface JsObservalbe {
    void addJsObserver(JsObserver jsObserver);
    void removeJsObserver(JsObserver jsObserver);
    void notice(String message);
}
