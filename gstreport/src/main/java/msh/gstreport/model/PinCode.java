/**
 * 
 */
package msh.gstreport.model;

import java.io.Serializable;

/**
 * @author sreehari
 *
 */
public class PinCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1821822130521420378L;

	private long id;

	private String zip;

	private String state;

	private String stateId;
	
	

	public PinCode(long id, String zip, String state, String stateId) {
		super();
		this.id = id;
		this.zip = zip;
		this.state = state;
		this.stateId = stateId;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
