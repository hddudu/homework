package com.hongdu.spring.beans.io;

import com.hongdu.spring.beans.config.v1.HdBeanDefinition;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName HdBeanDefinitionResource
 * @Description 获取流 :  暂时不知道咋用了
 * @Author dudu
 * @Date 2019/7/16 16:07
 * @Version 1.0
 */
public class HdBeanDefinitionResource extends HdAbstractResouce {

    private final HdBeanDefinition definition;

    public HdBeanDefinitionResource(HdBeanDefinition definition) {
        Assert.notNull(definition, "definition must not be null");
        this.definition = definition;
    }

    public HdBeanDefinition getDefinition() {
        return definition;
    }

    @Override
    public InputStream getInputStrema() throws IOException {
        //直接加载 流

        return null;
    }
}
