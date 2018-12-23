package cn.com.mvnbook.ssm.demo.dao.mybatis;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration//允许注解
@ComponentScan(basePackages = { "cn.com.mvnbook" })//注解类的基本包
public class MyBatisConfiguration {
	// 将方法结果按Bean管理，别的地方可以按类型进行装配
	@Bean(autowire = Autowire.BY_TYPE)
	public DataSource dataSource() {
		// 创建数据源
		BasicDataSource dataSource = new BasicDataSource();
		Properties prop = null;
		try {
			// 读资源文件中的数据库连接信息
			prop = Resources.getResourceAsProperties("db.properties");
			String driverClass = prop.getProperty("jdbc.driverClassName");
			String jdbcUrl = prop.getProperty("jdbc.url");
			String uname = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");

			dataSource.setDriverClassName(driverClass);
			dataSource.setUrl(jdbcUrl);
			dataSource.setUsername(uname);
			dataSource.setPassword(password);
			dataSource.setRemoveAbandonedTimeout(60);
			dataSource.setRemoveAbandoned(true);
			dataSource.setLogAbandoned(false);
			dataSource.setMinIdle(10);
			dataSource.setMinEvictableIdleTimeMillis(30000);
			dataSource.setMaxWait(10);
			dataSource.setInitialSize(2);
			dataSource.setMaxActive(10);
			dataSource.setTimeBetweenEvictionRunsMillis(30000);
			dataSource.setValidationQuery("SELECT 1");
			dataSource.setTestOnReturn(false);
			dataSource.setTestOnBorrow(true);
			dataSource.setTestWhileIdle(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSource;

	}
    // 将返回结果对象定义成一个name为sqlSessionFactory的bean
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean getSqlSesssionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		/*ClassPathResource re = new ClassPathResource("myBatisConfig.xml");
		bean.setConfigLocation(re);*/
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources;
		try {
			//设置MyBatis视图映射文件的路径
			resources = resolver.getResources("classpath:cn/com/mvnbook/ssm/demo/dao/mybatis/entity/*Mapper.xml");
			bean.setMapperLocations(resources);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bean;
	}
    // 创建Mapper和接口的绑定配置器Bean，名称为mapper
	@Bean(name = "mapper", autowire = Autowire.BY_NAME)
	public MapperScannerConfigurer getMapperScannerConfigurer() {
		MapperScannerConfigurer conf = new MapperScannerConfigurer();
		//设置要绑定的接口基本包路径
		conf.setBasePackage("cn.com.mvnbook.ssm.demo.dao.mybatis.impl");
		// 设置连接数据库的sqlSessionFactory
		conf.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return conf;
	}
}

