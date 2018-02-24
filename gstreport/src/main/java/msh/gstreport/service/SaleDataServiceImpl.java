package msh.gstreport.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msh.gstreport.dao.CommonDao;
import msh.gstreport.dao.SaleDao;
import msh.gstreport.main.ApplicationConfig2;
import msh.gstreport.model.PinCode;
import msh.gstreport.model.SaleData;
import msh.gstreport.model.Sku;
import msh.gstreport.utils.ConsoleLogger;
import msh.gstreport.utils.GSTRConstants;
import msh.gstreport.utils.GSTRUtils;

@Service(value = "saleDataService")
public class SaleDataServiceImpl implements SaleDataService {

	@Autowired
	SaleDao saleDao;

	@Autowired
	CommonDao commonDao;

	@Autowired
	ConsoleLogger log;

	@Autowired(required = true)
	ApplicationConfig2 applicationConfig;

	@Override
	public int storeSaleData() {
		List<SaleData> inputSaleDataList = GSTRUtils.parseSaleData(applicationConfig.getDataDirectoryPath());

		if (inputSaleDataList == null || inputSaleDataList.isEmpty()) {
			log.error("GSTRUtils.parseSaleData returned null/empty list");
			return GSTRConstants.GENERIC_FAILURE;
		}

		int count = 0;

		try {
			// Load pin and state pairs from DB
			// Map<String, String> pinStatePairs =
			// commonDao.loadPinCodeStatePairs();
			Map<String, PinCode> pinCodeMap = commonDao.loadPinCodeTable();

			if (pinCodeMap == null) {
				pinCodeMap = new HashMap<>();// to avoid null pointer exception
												// while look up
			}

			// Load sku and hsncode pairs from DB
			Map<String, Sku> skuMap = commonDao.loadSkuTable();

			if (skuMap == null) {
				skuMap = new HashMap<>();// to avoid null pointer
			}

			log.info("Inserting sale data records. . . please  wait");

			for (SaleData rawData : inputSaleDataList) {

				if (StringUtils.isNotBlank(rawData.getType())) {
					rawData.setType(rawData.getType().replaceAll("\"", ""));
				}
				if (StringUtils.isBlank(rawData.getType()) || (!rawData.getType().equalsIgnoreCase("Order")
						&& !rawData.getType().equalsIgnoreCase("Refund"))) {
					log.info("Ignoring record of type:" + rawData.getType());
					continue;
				}

				// if order_state value is not present in raw data & pin is
				// present, then look up
				// in the map

				if (StringUtils.isNotBlank(rawData.getOrderPostal())) {
					// log.info("state value is blank in raw data for
					// order-id:"+rawData.getOrderId());

					PinCode pinCode = pinCodeMap.get(rawData.getOrderPostal());
					if (pinCode != null) {
						String orderState = pinCode.getState();
						if (StringUtils.isBlank(orderState)) {
							log.error("LookUp:pin[" + rawData.getOrderPostal() + "] state[" + orderState + "]");
						} else {
							// log.info("LookUp:pin["+rawData.getOrderPostal()+"]
							// state["+orderState+"]");
							rawData.setOrderState(orderState);
						}
					} else {
						log.info("No PinCode row exist for the pin:" + rawData.getOrderPostal());
						if (StringUtils.isNotBlank(rawData.getOrderState())) {
							log.info("State value from input:" + rawData.getOrderState());
							rawData.setOrderState(rawData.getOrderState().toUpperCase());
						} else {
							log.info("State value from input is null:" + rawData.getOrderState());
						}
					}
				}

				// populate the hsnCode
				if (StringUtils.isNotBlank(rawData.getSku())) {
					Sku sku = skuMap.get(rawData.getSku());
					if (sku == null) {
						log.info("SKU record not found for the sku:" + rawData.getSku());
					} else if (StringUtils.isBlank(sku.getHsnCode())) {
						log.info("HSNCode is empty for the sku:" + rawData.getSku());
					} else {
						rawData.setHsnCode(sku.getHsnCode());
						log.info("HSNCode[" + sku.getHsnCode() + "] found for the  sku:" + rawData.getSku());
					}
				} else {
					log.info("SKU is blank for order-id:" + rawData.getOrderId());
				}

				saleDao.save(rawData);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while inserting sale data");
		}

		log.info("Inserted sala data records count:" + count);

		return GSTRConstants.GENERIC_SUCCESS;
	}

	@Override
	public List<SaleData> retrieveSaleReportData() {
		// TODO Auto-generated method stub
		return saleDao.fetchSaleReportData();
	}

	@Override
	public int generateSaleReport() {
		List<SaleData> saleDataList = saleDao.fetchSaleReportData();
		if (saleDataList == null || saleDataList.isEmpty()) {
			log.info("saleDao.fetchSaleReportData() returned null/empty report objects");
			return GSTRConstants.GENERIC_FAILURE;
		}

		String company = saleDataList.get(0).getCompany();
		if (StringUtils.isBlank(company)) {
			company = GSTRConstants.DEFAULT_COMPANY;
		}

		String outputFileName = applicationConfig.getStoreReportsInto() + File.separator + "B2C-statewise-"+company+".csv";
		try (OutputStream fos = new FileOutputStream(outputFileName)) {
			String header = GSTRConstants.SALE_REPORT_HEADER + "\n";
			fos.write(header.getBytes());

			for (SaleData sd : saleDataList) {
				StringBuilder sb = new  StringBuilder();
				sb.append("\"" + sd.getType() + "\"," + "\"" + sd.getOrderState() + "\","+ "\"" + sd.getTaxRate() + "\","+
						 "\"" + sd.getTaxableValue() + "\","+
						 "\"" + sd.getCessAmount() + "\","+
						 "\"" + sd.getEcommGSTIN() + "\"\n");
				fos.write(sb.toString().getBytes());
			}
		} catch (Exception e) {
			log.error("Error creating report");
			e.printStackTrace();
		}

		return GSTRConstants.GENERIC_SUCCESS;
	}

}