/**
 * 
 */
package msh.gstreport.dao;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import msh.gstreport.model.MTRData;
import msh.gstreport.utils.ConsoleLogger;

/**
 * @author sreehari
 *
 */
@Repository(value="mtrDao")
public class MTRDaoImpl implements MTRDao {
	
	@Autowired
	ConsoleLogger log;
	
	@Autowired
	DataSource  dataSource;

	/* (non-Javadoc)
	 * @see msh.gstreport.dao.MTRDao#save(msh.gstreport.model.InputMTRData)
	 */
	@Override
	public int save(MTRData data) {
		String invoiceNumber ="";
		if(StringUtils.isNotBlank(data.getInvoiceNumber())){
			invoiceNumber = data.getInvoiceNumber().replaceAll("\"", "");
		}
		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("company_name", "MEILI ECOMMERCE PRIVATE LIMITED")
				.addValue("invoice_number", invoiceNumber)
				.addValue("invoice_date", data.getInvoiceDate().replaceAll("\"", ""))
				.addValue("order_id", data.getOrderId())
				.addValue("invoice_id", new Long(invoiceNumber.substring(4, invoiceNumber.length())))
				.addValue("marketplace", "amazon") //todo
				.addValue("import_id", new  Long("1")) //to do
				.addValue("is_processed", new Integer("0"));//to do
		
		SimpleJdbcInsert si=new  SimpleJdbcInsert(dataSource).withTableName("btocdocsreport").usingGeneratedKeyColumns("id");
		return si.execute(paramSource);
	}

}
