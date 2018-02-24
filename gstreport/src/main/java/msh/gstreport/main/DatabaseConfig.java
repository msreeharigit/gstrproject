package msh.gstreport.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component(value="databaseConfig")
@ComponentScan(basePackages="msh.gstreport")
@PropertySource(value={"classpath:database.properties"})
public class DatabaseConfig {

	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setUrl(env.getRequiredProperty("jdbc.url"));
		ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		ds.setUsername(env.getRequiredProperty("jdbc.username"));
		ds.setPassword(env.getRequiredProperty("jdbc.password"));
		return ds;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
		NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(dataSource);
		return template;
	}
	
	@Bean
	public JdbcTemplate plainJdbcTemplate(DataSource dataSource){
		JdbcTemplate template=new JdbcTemplate(dataSource);
		return template;
	}
	
	
	
	
}
