package cn.com.mvnbook.ssh.demo.dao.hibernate.config;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//配置类
@Configuration
@EnableTransactionManagement// 要支持事务管理
@ComponentScan({ "cn.com.mvnbook.ssh.demo.dao.hibernate.config" })
@PropertySource(value = { "classpath:db.properties" })// 自动读入的属性文件
public class HibernateConfiguration {
	// 自动注入 Spring的环境对象(上下文)
    @Autowired
    private Environment environment;
 
    // 创建一个SessionFactory
    @Bean(name="sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        // 数据源
        sessionFactory.setDataSource(dataSource());
        // 指定数据实体类的包
        sessionFactory.setPackagesToScan(new String[] 
                                   { "cn.com.mvnbook.ssh.demo.entity.hibernate" });
        // hibernate的属性信息
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
    // 初始化数据源对象
    @Bean(name="dataSource")// 将当前方法返回的对象，当成普通Bean对象，放入IOC容器中
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 设置连接数据库的四要素
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    // 将Hibernate除连接数据库之外的配置，封装到Properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;        
    }
     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

