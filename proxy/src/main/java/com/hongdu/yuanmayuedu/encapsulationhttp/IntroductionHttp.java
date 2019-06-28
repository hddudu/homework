package com.hongdu.yuanmayuedu.encapsulationhttp;

/**
 * @ClassName IntroductionHttp
 * @Description
 *  HTTP协议的主要特点可概括如下：
 * 1.支持客户/服务器模式。
 * 2.简单快速：客户向服务器请求服务时，只需传送请求方法和路径。请求方法常用的有GET、HEAD、POST。
 *      每种方法规定了客户与服务器联系的类型不同。由于HTTP协议简单，使得HTTP服务器的程序规模小，因而通信速度很快。
 * 3.灵活：HTTP允许传输任意类型的数据对象。正在传输的类型由Content-Type加以标记。
 * 4.无连接：无连接的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并收到客户的应答后，即断开连接。采用这种方式可以节省传输时间。
 * 5.无状态：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，
 *  这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快。
 * --------------
 * @Author dudu
 * @Date 2019/6/24 9:17
 * @Version 1.0
 */
public class IntroductionHttp {
}