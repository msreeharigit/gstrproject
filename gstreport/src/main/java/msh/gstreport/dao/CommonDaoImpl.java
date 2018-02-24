package msh.gstreport.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import msh.gstreport.model.PinCode;
import msh.gstreport.model.Sku;
import msh.gstreport.utils.ConsoleLogger;

@Repository(value="commonDao")
public class CommonDaoImpl implements CommonDao {
	
	@Autowired
	ConsoleLogger log;
	
	@Autowired
	JdbcTemplate template;

	@Override
	public Map<String, String> loadPinCodeStatePairs() {
		String sql = "SELECT * FROM pincode";
		log.info("Loading pin<->state pairs . . .");
		Map<String,String> pinStatePairs =  new HashMap<>();
		List<Map<String,Object>> rows=template.queryForList(sql);
		for(Map<String,Object> row:rows){
			pinStatePairs.put((String)row.get("zip"), (String)row.get("state"));
			/*
			log.info(""+row.get("id"));
			log.info(""+row.get("zip"));
			log.info(""+row.get("state"));
			log.info(""+row.get("state_id"));
			*/
		}
		log.info("Loading pin<->state pairs . . .[Done]");
		return pinStatePairs;
	}
	
	
	@Override
	public Map<String, PinCode> loadPinCodeTable() {
		String sql = "SELECT * FROM pincode";
		log.info("Loading pin<->PinCode object . . .");
		Map<String,PinCode> pinPinCodeObjectMap =  new HashMap<>();
		List<Map<String,Object>> rows=template.queryForList(sql);
		for(Map<String,Object> row:rows){
			PinCode pinCode = new PinCode((Long)row.get("id"),(String)row.get("zip"),(String)row.get("state"),
					(String)row.get("state_id"));
			pinPinCodeObjectMap.put((String)row.get("zip"), pinCode);
			/*
			log.info(""+row.get("id"));
			log.info(""+row.get("zip"));
			log.info(""+row.get("state"));
			log.info(""+row.get("state_id"));
			*/
		}
		log.info("Loading pin<->PinCode object . . .[Done]");
		return pinPinCodeObjectMap;
	}


	@Override
	public Map<String, Sku> loadSkuTable() {
		String sql = "SELECT * FROM oc_sv_mapping";
		log.info("Loading sku<->Sku object . . .");
		Map<String,Sku> skuMap =  new HashMap<>();
		List<Map<String,Object>> rows=template.queryForList(sql);
		for(Map<String,Object> row:rows){
			Sku sku = new Sku((Long)row.get("id"),(String)row.get("MerchantSKU"),(String)row.get("Account"),(String)row.get("HSNCode"),
					(String)row.get("categoryId"));
			sku.setTax1((Integer)row.get("tax1"));
			sku.setTax2((Integer)row.get("tax2"));
			skuMap.put((String)row.get("MerchantSKU"), sku);
		}
		log.info("Loading sku<->Sku object . . .[Done]");
		return skuMap;
	}
}
