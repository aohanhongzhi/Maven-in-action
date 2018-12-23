package cn.com.mvnbook.ssm.demo.web.configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//继承初始化父类，让web服务器能自动识别，初始化SpringMVC Servlet入口组件
//代替在web.xml中配置org.springframework.web.servlet.DispatcherServlet
public class SpringMVCInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// 加载SpringMVC的基本配置类
		return new Class[] { SpringMVCConfiguration.class };
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	@Override
	protected String[] getServletMappings() {
		// 指定SpringMVC的mapping url-pattern
		return new String[] { "/" };
	}
}

