package msh.gstreport.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages="msh.gstreport")
@PropertySource(value={"classpath:application.properties"})
public class ApplicationConfig2 {

	@Autowired
	Environment env;
	
	@Value("${datadirectorypath}")
	private String dataDirectoryPath;
	
	
	@Value("${storereportsinto}")
	private String storeReportsInto;
	
	public void setDataDirectoryPath(String dataDirectoryPath) {
		this.dataDirectoryPath = dataDirectoryPath;
	}
	
	public String getDataDirectoryPath() {
		return dataDirectoryPath;
	}
	
	public String getStoreReportsInto() {
		return storeReportsInto;
	}
	
	public void setStoreReportsInto(String storeReportsInto) {
		this.storeReportsInto = storeReportsInto;
	}
}
