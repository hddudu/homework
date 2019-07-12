package com.hongdu.gupao.spring.iocbeanprocess.beans.xml;

import com.hongdu.gupao.spring.iocbeanprocess.BeanReference;
import com.hongdu.gupao.spring.iocbeanprocess.beans.AbstractBeanDefinitionReader;
import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanDefinition;
import com.hongdu.gupao.spring.iocbeanprocess.beans.PropertyValue;
import com.hongdu.gupao.spring.iocbeanprocess.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @ClassName XmlBeanDefinitionReader
 * @Description 通过xml文件方式读取BeanDefinition
 * @Author dudu
 * @Date 2019/7/12 9:07
 * @Version 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream is = getResourceLoader().getResources(location).getInputStream();
        doLoadBeanDefinitions(is);
    }

    /**
     * 加载beanDefinition
     * @param is
     * @throws Exception
     */
    private void doLoadBeanDefinitions(InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);

        //从xml解析bean
        registryBeanDefinitions(doc);
        if(is != null) {
            is.close();
        }
    }

    private void registryBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();

        //解析 beanDefinitions
        parseBeanDefinitions(root);
    }

    /**
     *  bean : id 和 class
     *  property ： name 和 value
     *  property : name 和 ref
     *  解析 bean 的过程
     * @param root
     */
    private void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 处理beanDefinition元数据
     * @param ele
     */
    private void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 处理属性 ： 依赖属性过程
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}