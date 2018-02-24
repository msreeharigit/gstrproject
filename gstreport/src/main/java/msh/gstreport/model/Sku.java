/**
 * 
 */
package msh.gstreport.model;

/**
 * @author sreehari Table name:oc_sv_mapping
 */

public class Sku {

	private long id;

	private String merchantSku;

	private String account;

	private String hsnCode;

	private String categoryId;

	private String category;

	private String categoryPath;

	private String rowPercent;

	private String descriptions;

	private int multipleSlab;

	private int tax1;

	private int tax2;
	
	public Sku(){
		
	}

	public Sku(long id, String merchantSku, String account, String hsnCode, String categoryId) {
		super();
		this.id = id;
		this.merchantSku = merchantSku;
		this.account = account;
		this.hsnCode = hsnCode;
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMerchantSku() {
		return merchantSku;
	}

	public void setMerchantSku(String merchantSku) {
		this.merchantSku = merchantSku;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getRowPercent() {
		return rowPercent;
	}

	public void setRowPercent(String rowPercent) {
		this.rowPercent = rowPercent;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public int getMultipleSlab() {
		return multipleSlab;
	}

	public void setMultipleSlab(int multipleSlab) {
		this.multipleSlab = multipleSlab;
	}

	public int getTax1() {
		return tax1;
	}

	public void setTax1(int tax1) {
		this.tax1 = tax1;
	}

	public int getTax2() {
		return tax2;
	}

	public void setTax2(int tax2) {
		this.tax2 = tax2;
	}

	
	
}
