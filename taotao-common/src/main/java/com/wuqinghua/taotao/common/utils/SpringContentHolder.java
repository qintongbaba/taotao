package com.wuqinghua.taotao.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by wuqinghua on 17/11/11.
 */
public class SpringContentHolder implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }


    public static Object getBean(String beanName) {
        return ac.getBean(beanName);
    }


    public static <T> T getBean(Class<T> clazz) {
        return ac.getBean(clazz);
    }
}
