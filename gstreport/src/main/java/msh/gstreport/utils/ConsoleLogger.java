package msh.gstreport.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service(value="logger")
public class ConsoleLogger {
	
	private SimpleDateFormat sdf = new  SimpleDateFormat("MM-dd-YYYY:HH:MM:SS");
	
	public void info(String msg){
		log("INFO:"+sdf.format(new Date())+":"+msg);
	}
	
	public void debug(String msg){
		log("DEBUG:"+sdf.format(new Date())+":"+msg);
	}
	
	public void error(String msg){
		log("ERROR:"+sdf.format(new Date())+":"+msg);
	}
	
	public void simple(String msg){
		log(msg);
	}
	
	private  void log(String msg){
		System.out.println(msg);
	}
}
