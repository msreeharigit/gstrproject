package msh.gstreport.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value="beanBag")
public class BeanBag {

	@Autowired
	@Qualifier(value="applicationConfig")
	ApplicationConfig config;
	
	public String name="ha";
	
}
