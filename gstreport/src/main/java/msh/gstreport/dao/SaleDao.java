package msh.gstreport.dao;

import java.util.List;

import msh.gstreport.model.SaleData;

public interface SaleDao {
	public int save(SaleData inputSaleDataObject);
	
	public int[] saveBatch(List<SaleData> rawSaleDataRowList);
	
	public List<SaleData> fetchSaleReportData();
}
