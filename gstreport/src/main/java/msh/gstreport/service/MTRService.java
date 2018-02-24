/**
 * 
 */
package msh.gstreport.service;

import java.util.List;

import msh.gstreport.model.MTRData;

/**
 * @author sreehari
 *
 */
public interface MTRService {

	public int  storeMTRData(List<MTRData> input);
}
