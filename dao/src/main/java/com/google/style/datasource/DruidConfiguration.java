package com.google.style.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author liangz
 * @date 2018/2/6 16:26
 * 数据库层配置
 **/
@Configuration
@MapperScan(basePackages = {"com.google.style.dao"})
@Data
public class DruidConfiguration {

    private Logger logger = Logger.getLogger(DruidConfiguration.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.validation-query}")
    private String validationQuery;
    @Value("${spring.datasource.max-open-prepared-statements}")
    private Integer maxOpenPreparedStatements;
    @Value("${spring.datasource.test-while-idle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.time-between-eviction-runs-millis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.max-wait}")
    private Long maxWait;
    @Value("${spring.datasource.max-active}")
    private Integer maxActive;
    @Value("${spring.datasource.min-idle}")
    private Integer minIdle;
    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.filters}")
    private String filters;

    /**
     * 初始化大小
     */
    private Integer initialSize = 5;
    private Boolean poolPreparedStatements = true;
    private Integer maxPoolPreparedStatementPerConnectionSize = 20;
    @Value("spring.datasource.connectionProperties")
    private String connectionProperties;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //数据源主配置
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        //数据源补充配置
        dataSource.setValidationQuery(validationQuery);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMaxWait(maxWait);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        //设置初始化大小
        dataSource.setInitialSize(initialSize);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 打开PSCache，并且指定每个连接上PSCache的大小 true
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setConnectionProperties(connectionProperties);
        try {
            dataSource.setFilters(filters);
            logger.info("Druid数据源初始化设置成功......");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Druid数据源filters设置失败......");
        }
        return dataSource;
    }

//    @Bean(name = "sqlSessionFactory")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
//        sessionFactory.setDataSource(dataSource);
//        return sessionFactory.getObject();
//    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean(name = "sqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate primarySqlSessionTemplate(
//            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

    /**
     * druid监控StatViewServlet
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        // ServletRegistrationBean提供类的进行注册
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加初始化参数：initParams
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "root");
        servletRegistrationBean.addInitParameter("loginPassword", "");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1, 115.236.176.90");
        // 是否能够重置数据，禁用HTML页面上的Reset All功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * Druid的StatFilter
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addServletNames("druidWebStatFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter(
                "exclusions", "*.js, *.gif, *.jpg, *.png, *.css, *.ico, /druid/*");
        return filterRegistrationBean;
    }
}
