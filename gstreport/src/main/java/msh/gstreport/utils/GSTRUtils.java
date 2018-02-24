package msh.gstreport.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import msh.gstreport.model.MTRData;
import msh.gstreport.model.SaleData;

/**
 * @author sreehari
 *
 */
public class GSTRUtils {

	private static ConsoleLogger log=new ConsoleLogger();
	
	/**
	 * Parse MTR input file
	 * @param path
	 * @return
	 */
	public static List<MTRData> parseMtrData(String path){
		if(StringUtils.isBlank(path)){
			log.info("unable to parse MTR data as input path value:"+path);
			return null;
		}
		File	directoryPath = new File(path);
		if(!directoryPath.isDirectory()){
			log.info("path["+path+"] is not a directory");
			return null;
		}
		
		List<File> files =Arrays.asList(directoryPath.listFiles())
				.stream()
				.filter((file)->file.getName().endsWith("-mtr.csv"))
				.collect(Collectors.toList());
		//files.stream().forEach((file)->System.out.println(file.getName()));
		
		if(files.isEmpty()){
			log.info("files ends with -mtr not found in the path:"+path);
			return null;
		}
		
		List<MTRData> list=new LinkedList<>();
		for(File file: files){
			log.info("Parsing the file:"+file.getName());
			
			try(InputStream is = new FileInputStream(file);) {
				int data=0,rowCount=0;
				StringBuffer sb = new StringBuffer();
				while((data=is.read())!=-1){
					if((char)data=='\r'){
						rowCount++;
						list.add(convertRawToInputMTRData(sb));
						sb=new StringBuffer();
					}
					sb.append((char)data);
				}
				log.info("count["+(rowCount-1)+"]");
			} catch (Exception e) {
				log.error("Unable to process file:"+file.getName());
				e.printStackTrace();
			}
			
			for(MTRData imd:list){
				System.out.println(imd);
			}
			
		}//  end for
		list.remove(0);//removing headers
		return list;
	}
	
	/**
	 * @param path This will be directory path
	 * @return
	 */
	public static List<SaleData> parseSaleData(String path){
		
		if(StringUtils.isBlank(path)){
			log.info("unable to parse sale data as input path value:"+path);
			return null;
		}
		
		File	directoryPath = new File(path);
		if(!directoryPath.isDirectory()){
			log.info("path["+path+"] is not a directory");
			return null;
		}
		
		//List<String> list = Arrays.asList(directoryPath.list()).stream().filter((fileName)->fileName.endsWith("-sale.csv")).collect(Collectors.toList());
		//list.stream().forEach((name)->System.out.println(name));
		List<File> files =Arrays.asList(directoryPath.listFiles())
				.stream()
				.filter((file)->file.getName().endsWith("-sale.csv"))
				.collect(Collectors.toList());
		//files.stream().forEach((file)->System.out.println(file.getName()));
		
		if(files.isEmpty()){
			log.info("files ends with -sale not found in the path:"+path);
			return null;
		}
		
		List<SaleData> allRecords=new LinkedList<>();
		for(File file: files){
			log.info("Parsing the file:"+file.getName());
			List<SaleData> records=new LinkedList<>();
			try(InputStream is = new FileInputStream(file);) {
				int data=0,rowCount=0;
				StringBuffer sb = new StringBuffer();
				while((data=is.read())!=-1){
					if((char)data=='\r'){
						rowCount++;
						records.add(convertRawToInputSaleData(sb));
						sb=new StringBuffer();
					}
					sb.append((char)data);
				}
				log.info("count["+(rowCount-1)+"]");
			} catch (FileNotFoundException e) {
				log.error("Unable to process file:"+file.getName());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			records.remove(0);//removing column headers object  at  zero'eth index
			allRecords.addAll(records);
			
			/*for(InputSaleData isd:list){
				System.out.println(isd);
			}*/
			
		}//  end for
		
		return allRecords;
	}
	
	/**
	 * Column headers for ref:
	 * {date/time}{"settlement id"}{"type"}{order id}{Sku}{"description"}{quantity}
	 * {"marketplace"}{fulfillment}{"order city"}{"order state"}{"order postal"}{"product sales"}
	 * {"shipping credits"}{"promotional rebates"}{Total sales tax liable(GST before adjusting TCS)}
	 * {TCS-CGST}{TCS-SGST}{TCS-IGST}{"selling fees"}{"fba fees"}{"other transaction fees"}{"other"}{"total"}
	 * @param sb
	 * @return
	 */
	private static SaleData convertRawToInputSaleData(StringBuffer sb){
		String trimmed=sb.toString().replaceAll("\n", "").replaceAll("\r", "");
		//System.out.println("["+trimmed+"]");
		String[] tokens=trimmed.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		SaleData data=new  SaleData();
		data.setDateTime(tokens[0]);
		data.setSettlementId(tokens[1]);
		data.setType(tokens[2]);
		data.setOrderId(tokens[3]);
		data.setSku(tokens[4]);
		data.setDescription(tokens[5]);
		data.setQuantity(tokens[6]);
		data.setMarketPlace(tokens[7]);
		data.setFulfillment(tokens[8]);
		data.setOrderCity(tokens[9]);
		data.setOrderState(tokens[10]);
		data.setOrderPostal(tokens[11]);
		data.setProductSales(tokens[12]);
		data.setShippingCredits(tokens[13]);
		data.setPromotionalRebates(tokens[14]);
		data.setTotalSalesTaxLiable(tokens[15]);
		data.setCgst(tokens[16]);
		data.setSgst(tokens[17]);
		data.setIgst(tokens[18]);
		data.setSellingFees(tokens[19]);
		data.setFbaFees(tokens[20]);
		data.setOtherTransFees(tokens[21]);
		data.setOther(tokens[22]);
		data.setTotal(tokens[23]);
		return data;
	}
	
	private static MTRData convertRawToInputMTRData(StringBuffer sb){
		String trimmed=sb.toString().replaceAll("\n", "").replaceAll("\r", "");
		//System.out.println("["+trimmed+"]");
		String[] tokens=trimmed.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		
		MTRData	inputMTRData = new  MTRData();
		inputMTRData.setInvoiceNumber(tokens[1]);
		inputMTRData.setInvoiceDate(tokens[2]);
		inputMTRData.setOrderId(tokens[4]);
		return inputMTRData;
	}
	
}
