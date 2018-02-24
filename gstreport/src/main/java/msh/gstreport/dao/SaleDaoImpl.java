package msh.gstreport.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import msh.gstreport.model.SaleData;
import msh.gstreport.utils.ConsoleLogger;

@Repository(value = "saleDao")
public class SaleDaoImpl implements SaleDao {

	@Autowired
	ConsoleLogger log;

	@Autowired
	NamedParameterJdbcTemplate template;

	@Autowired
	DataSource dataSource;

	public SaleDaoImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int save(SaleData rawData) {
		int res1 = createSaleRecord(rawData);
		// log.info("sale record returned:"+res1);
		int res2 = createB2CSalesDataRecord(rawData);
		// log.info("b2c sale record returned:"+res2);
		return 1;
	}

	private int createSaleRecord(SaleData rawData) {
		SqlParameterSource paramSource = new MapSqlParameterSource().addValue("datetime1", rawData.getDateTime())
				.addValue("settlement_id", rawData.getSettlementId())
				.addValue("type1", rawData.getType().replaceAll("\"", "")).addValue("order_id", rawData.getOrderId())
				.addValue("sku", rawData.getSku())
				.addValue("description1", rawData.getDescription().replaceAll("\"", ""))
				.addValue("quantity", rawData.getQuantity()).addValue("marketplace", rawData.getMarketPlace())
				.addValue("fulfillment", rawData.getFulfillment()).addValue("order_city", rawData.getOrderCity())
				.addValue("order_state", rawData.getOrderState()).addValue("order_postal", rawData.getOrderPostal())
				.addValue("product_sales", rawData.getProductSales().replaceAll("\"", "").replaceAll(",", ""))
				.addValue("shipping_credits", rawData.getShippingCredits().replaceAll("\"", ""))
				.addValue("promotional_rebates", rawData.getPromotionalRebates())
				.addValue("sales_tax_liable", rawData.getTotalSalesTaxLiable().replaceAll("\"", "").replaceAll(",", ""))
				.addValue("sales_tax_collected", "")
				.addValue("selling_fees", rawData.getSellingFees().replaceAll("\"", ""))
				.addValue("fba_fees", rawData.getFbaFees().replaceAll("\"", ""))
				.addValue("other_transaction_fees", rawData.getOtherTransFees().replaceAll("\"", ""))
				.addValue("other", rawData.getOther().replaceAll("\"", ""))
				.addValue("total", rawData.getTotal().replaceAll("\"", "").replaceAll(",", ""))
				.addValue("company_name", "MEILI ECOMMERCE PRIVATE LIMITED") // need
																				// to
																				// figureout
				.addValue("is_processed", 0) // need get it from input directory
												// year+month
				.addValue("import_id", 2) // id from oc_sv_company table
				.addValue("created_time", new Date());
		SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("amazon_salesdata")
				.usingGeneratedKeyColumns("id");
		return si.execute(paramSource);
	}

	private int createB2CSalesDataRecord(SaleData rawData) {
		SqlParameterSource paramSource = new MapSqlParameterSource().addValue("order_id", rawData.getOrderId())
				.addValue("order_date", rawData.getDateTime()).addValue("company", "MEILI ECOMMERCE PRIVATE LIMITED")
				.addValue("sales_value", rawData.getProductSales().replaceAll("\"", "").replaceAll(",", ""))
				.addValue("shipping_cost", "0").addValue("zip", rawData.getOrderPostal())
				.addValue("state_name", rawData.getOrderState()).addValue("state", rawData.getOrderState())
				.addValue("qty", rawData.getQuantity())
				.addValue("product_title", rawData.getDescription().replaceAll("\"", ""))
				.addValue("sku", rawData.getSku()).addValue("hsncode", rawData.getHsnCode())
				.addValue("marketplace", rawData.getMarketPlace())
				.addValue("status", rawData.getType().replaceAll("\"", "")).addValue("import_id", "2")// to
																										// do
				.addValue("is_processed", "0")// to do
				.addValue("created_time", new Date()).addValue("promotional_rebates", rawData.getPromotionalRebates())
				.addValue("sales_tax_liable",
						rawData.getTotalSalesTaxLiable().replaceAll("\"", "").replaceAll(",", ""));

		SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("btocsalesdata")
				.usingGeneratedKeyColumns("id");
		return si.execute(paramSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see msh.gstreport.dao.SaleDao#saveBatch(List) TO DO: to improve
	 * performance, do batch update
	 */
	@Override
	public int[] saveBatch(List<SaleData> rawSaleDataRowList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleData> fetchSaleReportData() {
		String query = "SELECT sales.id,product_title,company,sku,order_date,order_id,order_item_id,qty,sales_value,"
				+ "multiple_slab,tax1,tax2,sales_value as total_sales_value,qty as total_qty,status,state_id,"
				+ "state_name,marketplace,shipping_cost as total_shipping_cost,"
				+ "promotional_rebates,sales_tax_liable," + "CASE" + " WHEN multiple_slab=1 and (sales_value/qty)>1000"
				+ " THEN tax2 ELSE tax1 END AS tax3"
				+ " FROM gstr.btocsalesdata as sales INNER JOIN gstr.oc_sv_mapping as map on "
				+ " map.MerchantSKU=sales.sku WHERE company LIKE :company and state_name!='' and is_processed='0'"
				+ "AND ( " + " CASE " + "WHEN marketplace LIKE 'amazon.in' THEN status IN('Order')"
				+ "WHEN marketplace LIKE 'Flipkart' THEN status IN('Sale','Return Cancellation')"
				+ "WHEN marketplace LIKE 'paytm' THEN status IN('Delivered','Shipped','Return Rejected')"
				+ "WHEN marketplace LIKE 'shopclues' THEN status IN('Complete','Delivered','Shipped')"
				+ "WHEN marketplace LIKE 'ebay' THEN status IN('Delivered')"
				+ "END) order by state_name ASC ,tax3 DESC,marketplace DESC";

		Map<String, SaleData> statewiseData = new HashMap<>();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("company", "MEILI ECOMMERCE PRIVATE LIMITED");
		List<Map<String, Object>> result = template.queryForList(query, paramMap);
		for (Map<String, Object> row : result) {
			SaleData record = new SaleData();
			record.setId((Long) row.get("id"));
			record.setDescription((String) row.get("product_title"));
			record.setCompany((String) row.get("company"));
			record.setSku((String) row.get("sku"));
			record.setDateTime((String) row.get("order_date"));
			record.setOrderId((String) row.get("order_id"));
			record.setQuantity((String) row.get("qty"));
			record.setSalesValue(Float.valueOf((String) row.get("sales_value")));
			record.setMultipleSlab((Integer) row.get("multiple_slab"));
			record.setTax1((Integer) row.get("tax1"));
			record.setTax2((Integer) row.get("tax2"));
			record.setTotalSalesValue(Float.valueOf((String) row.get("total_sales_value")));
			record.setTotalQty(Integer.valueOf((String) row.get("total_qty")));
			record.setType((String) row.get("status"));

			record.setMarketPlace((String) row.get("marketplace"));
			record.setTotalShippingCost(Float.valueOf((String) row.get("total_shipping_cost")));
			record.setPromotionalRebates((String) row.get("promotional_rebates"));
			record.setTotalSalesTaxLiable((String) row.get("sales_tax_liable"));
			record.setTax3((Long) row.get("tax3"));

			// set final report values
			record.setType("OE");/// Type:hardcoded, OE means Other than
									/// ECommerce
			record.setOrderState((String) row.get("state_name"));// Place of
																	// Supply

			float taxableValue = 0;
			int taxRate = 0;
			float salesValue = 0;
			try {
				salesValue = record.getTotalSalesValue() + record.getTotalShippingCost()
						+ Float.parseFloat(record.getPromotionalRebates())
						+ Float.parseFloat(record.getTotalSalesTaxLiable());
				// log.info("Calculated Sales Value:" + salesValue);
				float tempSalesValue = salesValue / record.getTotalQty();
				// log.info("Calculated tempSales Value:" + tempSalesValue);

				if (record.getMultipleSlab() == 1) {
					if (tempSalesValue <= 1000) {
						taxableValue = salesValue
								- (salesValue - (salesValue * ((float) 100 / (float) (100 + record.getTax1()))));
						taxRate = record.getTax1();
					} else {
						taxableValue = salesValue
								- (salesValue - (salesValue * ((float) 100 / (float) (100 + record.getTax2()))));
						taxRate = record.getTax1();
					}
				} else {
					taxableValue = salesValue
							- (salesValue - (salesValue * ((float) 100 / (float) (100 + record.getTax1()))));
					taxRate = record.getTax1();
				}
			} catch (Exception e) {
				log.error("Error calculating Taxable Value" + e);
				e.printStackTrace();
			}

			record.setTaxRate(taxRate);// Rate
			record.setTaxableValue(taxableValue);// Taxable Value
			record.setCessAmount((float) 0.0);
			record.setEcommGSTIN("29AAICA3918J1ZE");
			//log.simple(record.getType() + "," + record.getOrderState() + "," + record.getTaxRate() + ","
				//	+ record.getTaxableValue() + "," + record.getCessAmount() + "," + record.getEcommGSTIN());

			String compositeKey = record.getOrderState() + "_" + record.getTaxRate();
			SaleData sd = statewiseData.get(compositeKey);
			if (sd != null) {
				sd.setTaxableValue(sd.getTaxableValue() + record.getTaxableValue());
			} else {
				sd = new SaleData();
				sd.setCompany(record.getCompany());
				sd.setType(record.getType());
				sd.setOrderState(record.getOrderState());
				sd.setTaxRate(record.getTaxRate());
				sd.setTaxableValue(record.getTaxableValue());
				sd.setCessAmount(record.getCessAmount());
				sd.setEcommGSTIN(record.getEcommGSTIN());
				statewiseData.put(compositeKey, sd);
			}
		}
		List<SaleData> list=new ArrayList<>();
		list.addAll(statewiseData.values());
		Collections.sort(list);
		for(SaleData record:list){
			log.simple(record.getType() + "," + record.getOrderState() + "," + record.getTaxRate() + ","
					+ record.getTaxableValue() + "," + record.getCessAmount() + "," + record.getEcommGSTIN());
		}
		return list;
	}
}
