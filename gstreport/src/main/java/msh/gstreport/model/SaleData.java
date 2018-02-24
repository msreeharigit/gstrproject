package msh.gstreport.model;

import java.io.Serializable;

public class SaleData implements Serializable,Comparable<SaleData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6212028677634283659L;

	private long id;

	private String dateTime;

	private String settlementId;

	private String type;

	private String orderId;

	private String sku;

	private String description;

	private String quantity;

	private String marketPlace;

	private String fulfillment;

	private String orderCity;

	private String orderState;

	private String orderPostal;

	private String productSales;

	private String shippingCredits;

	private String promotionalRebates;

	// Total sales tax liable(GST before adjusting TCS)
	private String totalSalesTaxLiable;

	// TCS-CGST
	private String cgst;

	// TCS-SGST
	private String sgst;

	// TCS-IGST
	private String igst;

	private String sellingFees;

	private String fbaFees;

	private String otherTransFees;

	private String other;

	private String total;

	private String hsnCode;

	private String company;

	private int multipleSlab;

	private Float salesValue;

	private Float totalSalesValue;

	private int totalQty;

	private Float totalShippingCost;

	private int tax1;

	private int tax2;

	private long tax3;

	private Float taxableValue;

	private Float cessAmount;

	private String ecommGSTIN;

	private int taxRate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Float getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(Float taxableValue) {
		this.taxableValue = taxableValue;
	}

	public Float getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(Float cessAmount) {
		this.cessAmount = cessAmount;
	}

	public String getEcommGSTIN() {
		return ecommGSTIN;
	}

	public void setEcommGSTIN(String ecommGSTIN) {
		this.ecommGSTIN = ecommGSTIN;
	}

	public int getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
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

	public long getTax3() {
		return tax3;
	}

	public void setTax3(long tax3) {
		this.tax3 = tax3;
	}

	public int getMultipleSlab() {
		return multipleSlab;
	}

	public void setMultipleSlab(int multipleSlab) {
		this.multipleSlab = multipleSlab;
	}

	public Float getSalesValue() {
		return salesValue;
	}

	public void setSalesValue(Float salesValue) {
		this.salesValue = salesValue;
	}

	public Float getTotalSalesValue() {
		return totalSalesValue;
	}

	public void setTotalSalesValue(Float totalSalesValue) {
		this.totalSalesValue = totalSalesValue;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public Float getTotalShippingCost() {
		return totalShippingCost;
	}

	public void setTotalShippingCost(Float totalShippingCost) {
		this.totalShippingCost = totalShippingCost;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}

	public String getFulfillment() {
		return fulfillment;
	}

	public void setFulfillment(String fulfillment) {
		this.fulfillment = fulfillment;
	}

	public String getOrderCity() {
		return orderCity;
	}

	public void setOrderCity(String orderCity) {
		this.orderCity = orderCity;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderPostal() {
		return orderPostal;
	}

	public void setOrderPostal(String orderPostal) {
		this.orderPostal = orderPostal;
	}

	public String getProductSales() {
		return productSales;
	}

	public void setProductSales(String productSales) {
		this.productSales = productSales;
	}

	public String getShippingCredits() {
		return shippingCredits;
	}

	public void setShippingCredits(String shippingCredits) {
		this.shippingCredits = shippingCredits;
	}

	public String getPromotionalRebates() {
		return promotionalRebates;
	}

	public void setPromotionalRebates(String promotionalRebates) {
		this.promotionalRebates = promotionalRebates;
	}

	public String getTotalSalesTaxLiable() {
		return totalSalesTaxLiable;
	}

	public void setTotalSalesTaxLiable(String totalSalesTaxLiable) {
		this.totalSalesTaxLiable = totalSalesTaxLiable;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getSellingFees() {
		return sellingFees;
	}

	public void setSellingFees(String sellingFees) {
		this.sellingFees = sellingFees;
	}

	public String getFbaFees() {
		return fbaFees;
	}

	public void setFbaFees(String fbaFees) {
		this.fbaFees = fbaFees;
	}

	public String getOtherTransFees() {
		return otherTransFees;
	}

	public void setOtherTransFees(String otherTransFees) {
		this.otherTransFees = otherTransFees;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "InputSaleData [dateTime=" + dateTime + ", settlementId=" + settlementId + ", type=" + type
				+ ", orderId=" + orderId + ", sku=" + sku + ", description=" + description + ", quantity=" + quantity
				+ ", marketPlace=" + marketPlace + ", fulfillment=" + fulfillment + ", orderCity=" + orderCity
				+ ", orderState=" + orderState + ", orderPostal=" + orderPostal + ", productSales=" + productSales
				+ ", shippingCredits=" + shippingCredits + ", promotionalRebates=" + promotionalRebates
				+ ", totalSalesTaxLiable=" + totalSalesTaxLiable + ", cgst=" + cgst + ", sgst=" + sgst + ", igst="
				+ igst + ", sellingFees=" + sellingFees + ", fbaFees=" + fbaFees + ", otherTransFees=" + otherTransFees
				+ ", other=" + other + ", total=" + total + "]";
	}

	@Override
	public int compareTo(SaleData o) {
		return this.getOrderState().compareTo(o.getOrderState());
	}
}
