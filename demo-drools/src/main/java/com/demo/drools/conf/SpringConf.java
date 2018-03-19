package com.demo.drools.conf;

import javax.sql.DataSource;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.demo.*")
@EnableTransactionManagement
public class SpringConf {
	
	private static final String drlFile = "rules/demo-message.drl";
	 
    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
 
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
 
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
    
    @Bean
    public DataSource datasource(){
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();    	 
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/ergcp");
    	dataSource.setUsername("root");
    	dataSource.setPassword("password");    	
    	return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(){
    	return new JdbcTemplate(datasource());
    }
    
    @Bean
    public DataSourceTransactionManager txManager() {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(datasource());
        return transactionManager;
    }

}
