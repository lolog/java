package net.ehcache.context;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");
	private static final ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) applicationContext;
	private static final BeanDefinitionRegistry registry = (BeanDefinitionRegistry) configurableContext.getBeanFactory();
	
	
	private AppContext() {
		super();
	}

	public static ApplicationContext getInstance() {
		return applicationContext;
	}
	
	public static BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}
	
	public static String[] getBeanNamesForType(Class<?> clazz) {
		return applicationContext.getBeanNamesForType(clazz);
	}
	
	public static void registerBean(String beanName, String beanClassName) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClassName);
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		registry.registerBeanDefinition(beanName, beanDefinition);
	}
}
