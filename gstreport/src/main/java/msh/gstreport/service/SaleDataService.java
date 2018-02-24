package msh.gstreport.service;

import java.util.List;

import msh.gstreport.model.SaleData;

public interface SaleDataService {

	public int storeSaleData();
	
	public int generateSaleReport();
	
	public List<SaleData> retrieveSaleReportData();
}
