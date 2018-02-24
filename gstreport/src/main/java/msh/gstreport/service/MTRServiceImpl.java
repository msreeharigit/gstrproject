/**
 * 
 */
package msh.gstreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msh.gstreport.dao.MTRDao;
import msh.gstreport.model.MTRData;
import msh.gstreport.utils.ConsoleLogger;

/**
 * @author sreehari
 *
 */
@Service(value="mtrService")
public class MTRServiceImpl implements MTRService {

	@Autowired
	ConsoleLogger log;
	
	@Autowired
	MTRDao	mtrDao;
	
	/* (non-Javadoc)
	 * @see msh.gstreport.service.MTRService#storeMTRData(java.util.List)
	 */
	@Override
	public int storeMTRData(List<MTRData> input) {
		for (MTRData rawData : input) {
			log.info("save mtr data invoice no:" + rawData.getInvoiceNumber());
			try {
				mtrDao.save(rawData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
}
