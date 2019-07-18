package com.hongdu.gupao.chain;

import com.hongdu.gupao.chain.annotation.FixedPrivPrice;
import com.hongdu.gupao.chain.factory.IPriceStrategyFactory;
import com.hongdu.gupao.chain.price.PriceStrategy;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PriceStrategyFactory
 * @Description 价格优惠工厂
 *  1: 不符合价格， 使用默认
 *  2： 符合价格， 使用找到的策略
 * @Author dudu
 * @Date 2019/7/18 17:04
 * @Version 1.0
 */
public class PriceStrategyFactory implements IPriceStrategyFactory {

    @Override
    public PriceStrategy createAndGet(double price) {
        PriceStrategy ps = findPriceStrategyInHolder(price);
        //如果没有找到符合的处理价格的类
        //那么就是用默认的策略处理类
        if(null == ps) {
            ps = PriceStrategyHolder.DEFAULT_PRICE_STRATEGY;
        }
        return ps;
    }

    /**
     * 处理符合价格的策略类
     * @param price
     * @return
     */
    private PriceStrategy findPriceStrategyInHolder(double price) {
        for (PriceStrategy ps : PriceStrategyHolder.priceStrategies) {
            FixedPrivPrice pps = ps.getClass().getAnnotation(FixedPrivPrice.class);
            if(matchCondition(price, pps)) {
                return ps;
            }
        }
        return null;
    }

    /**
     * 注解比较值大小
     * @param price
     * @param pps
     * @return
     */
    private boolean matchCondition(double price, FixedPrivPrice pps) {
        return price >= pps.min() && price <= pps.max();
    }

    /**
     * 内部类 ：
     *
     */

    static private class PriceStrategyHolder {
        /**
         * 默认的 策略实现
         */
        static private final PriceStrategy DEFAULT_PRICE_STRATEGY = new DefaultPriceStrategy();

        /**
         * 价格优惠容器 :
         *      责任链模式 关联到了 容器
         */
        static private Set<PriceStrategy> priceStrategies = new HashSet<>();

        /**
         * 内部静态工具类
         */
        static final private LoadPriceStrategyUtil LOAD_UTIL = new LoadPriceStrategyUtil();

        static {
            //将添加注解的类都加载容器中去
            LOAD_UTIL.classLoader(priceStrategies);
        }
    }

    /**
     * 内部类 ： PriceStrategyFactory $ LoadPriceStrategyUtil
     * 内部工具类 ： 加载价格策略的 工具类
     */
    static private class LoadPriceStrategyUtil {
        //策略类的路径
        static final private String STRATEGY_URL = "com.hongdu.gupao.chain";

        /**
         * 加载 策略类
         * @param pss
         */
        void classLoader(Set<PriceStrategy> pss) {
            String[] classFiles = findClassFromPath();
            if(classFiles == null) return;
            //动态类加载器
            DynamicLoaderClass dlc = new DynamicLoaderClass();
            for(String classFileName : classFiles) {
                try {
                    String className = generateClassName(classFileName);
                    //根据路径名曲加载类
                    Class<?> cls = dlc.loadClass(className);
                    //获取注解 : 获取加载的类的注解 ： 如果有 ； 如果没有
                    FixedPrivPrice fpp = cls.getAnnotation(FixedPrivPrice.class);
                    if(fpp != null) {
                        pss.add((PriceStrategy) cls.newInstance());
                    }

                } catch (Exception e) {
                    System.out.println("加载类失败！！！");
                }
            }
        }

        /**
         * 生成类的路径名
         * @param classFileName
         * @return
         */
        private String generateClassName(String classFileName) {
            return STRATEGY_URL + "." + classFileName.substring(0, classFileName.indexOf("."));
        }

        private String[] findClassFromPath() {
            //符号替换
            String loaderUrl = STRATEGY_URL.replace(".", "/");
            URL url = ClassLoader.getSystemResource(loaderUrl);
            if(null == url) {
                return null;
            }
            //文件过滤器
            String[] retFilePaths = new File(url.getPath()).list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".class");
                }
            });
            return retFilePaths;
        }

    }
}
