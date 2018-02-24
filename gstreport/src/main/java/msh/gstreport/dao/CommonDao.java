package msh.gstreport.dao;

import java.util.Map;

import msh.gstreport.model.PinCode;
import msh.gstreport.model.Sku;

public interface CommonDao {

	public Map<String,String> loadPinCodeStatePairs();

	public Map<String, PinCode> loadPinCodeTable();
	
	public Map<String,Sku> loadSkuTable();
}
