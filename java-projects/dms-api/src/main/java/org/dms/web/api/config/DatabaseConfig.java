package org.dms.web.api.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
		"org.dms.web.api.dao" }, transactionManagerRef = "txManager", entityManagerFactoryRef = "entityMgrFactory")
public class DatabaseConfig {

	@Autowired
	private Environment env;

	@Bean(name = "dataSourceProperties")
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "dataSource")
	public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate JdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "entityMgrFactory")
	public LocalContainerEntityManagerFactoryBean entityMgrFactory(@Qualifier("dataSource") DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { env.getProperty("app.hibernate.packages-to-scan") });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("app.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("app.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("app.hibernate.show-sql"));
		properties.put("hibernate.format_sql", env.getProperty("app.hibernate.format-sql"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean(name = "txManager")
	public PlatformTransactionManager txManager(
			final @Qualifier("entityMgrFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

}
