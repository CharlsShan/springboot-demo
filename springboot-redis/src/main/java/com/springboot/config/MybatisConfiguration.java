package com.springboot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.springboot.mapper")
public class MybatisConfiguration {
	private static Log logger = LogFactory.getLog(MybatisConfiguration.class);
	@Autowired
	private DataSource dataSource;

	// 提供SqlSeesion
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		// 添加pageHelper插件
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageHelper() });
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		//这里最好把路径写进yml中，之后通过@Value注入，减少硬编码，这里只是举个反例
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		//把数据源放进spring事务管理器
		return new DataSourceTransactionManager(dataSource);
	}

}