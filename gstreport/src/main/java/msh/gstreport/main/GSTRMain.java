package msh.gstreport.main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import msh.gstreport.model.MTRData;
import msh.gstreport.model.SaleData;
import msh.gstreport.service.MTRService;
import msh.gstreport.service.SaleDataService;
import msh.gstreport.utils.ConsoleLogger;
import msh.gstreport.utils.GSTRConstants;
import msh.gstreport.utils.GSTRUtils;

public class GSTRMain {

	private static ConsoleLogger log = new ConsoleLogger();

	public static void main(String[] args) {

		/*
		 * Get file list from configured path
		 * 
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ApplicationConfig2.class);
		context.register(DatabaseConfig.class);
		context.refresh();
		ApplicationConfig2 appConfig = context.getBean(ApplicationConfig2.class);
		log.info(appConfig.getDataDirectoryPath());
		
		//List<SaleData> inputSaleDataList = GSTRUtils.parseSaleData(appConfig.getDataDirectoryPath());
		//List<InputMTRData> inputMTRDataList = GSTRUtils.parseMtrData(appConfig.getDataDirectoryPath());
		//DatabaseConfig dbConfig = context.getBean(DatabaseConfig.class);
		//log.info("DB:Url" + dbConfig.env.getProperty("jdbc.url"));

		SaleDataService service = (SaleDataService) context.getBean("saleDataService");
		int storeStatus =1;// service.storeSaleData();
		if( storeStatus!=GSTRConstants.GENERIC_FAILURE){
			int status = service.generateSaleReport();
		}
		//service.retrieveSaleReportData();
		
		//MTRService mtrService = (MTRService) context.getBean("mtrService");
		//mtrService.storeMTRData(inputMTRDataList);

		context.close();
	}
}
