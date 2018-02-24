/**
 * 
 */
package msh.gstreport.model;

import java.io.Serializable;

/**
 * @author sreehari
 *
 */
public class MTRData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1029856889944960071L;

	//private String sellerGSTN;
	
	private String invoiceNumber;
	
	private String invoiceDate;
	
	//private String transactionType;
	
	private String orderId;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "InputMTRData [invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", orderId=" + orderId
				+ "]";
	}
	
	
	
	/*
	private String shipmentId;
	
	private String shipmentDate;
	
	private String orderDate;
	
	private String shipmentItemId;
	
	private String quantity;
	
	private String itemDescription;
	
	private String asin;
	
	private String hsn;
	
	private String sku;
	
	private String productTaxCode;
	
	private String billFromCity;
	
	private String billFromState;
	
	private String billFromCountry;
	
	private String billFromPostalCode;
	
	private String shipFromCity;
	
	private String shipFromState;
	
	private String shipFromCountry;
	
	private String shipFromPostalCode;
	
	private String shipToCity;
	
	private String shipToState;
	
	private String shipToCountry;
	
	private String shipToPostalCode;
	
	private String invoiceAmount;
	
	private String taxEclusiveGross;
	
	private String totalTaxAmount;
	
	private String cgstRate;
	
	private String sgstRate;
	
	private String utgstRate;
	
	private String igstRate;
	
	private String compCessRate;
	
	private String  principalAmount;
	
	private String pricipalAmountBasis;
	
	private String cgstTax;
	
	private String sgstTax;
	
	private String igstTax;
	
	private String utgstTax;
	
	private String  compCessTax;
	
	private String shippingAmount;
	
	private String shippingAmountBasis;
	
	private String  shippingCgstTax;
	
	private String shippingSgstTax;
	
	private String shippingUtgstTax;
	
	private  String shippingIgstTax;
	
	private  String shippingCessTaxAmount;
	*/
	
	
	/*
	 * Add below csv headers as properties if required to parse
	"Gift Wrap Amount","Gift Wrap Amount Basis","Gift Wrap Cgst Tax","Gift Wrap Sgst Tax","Gift Wrap Utgst Tax",
	"Gift Wrap Igst Tax","Gift Wrap Compensatory Cess Tax","Item Promo Discount","Item Promo Discount Basis",
	"Item Promo Tax","Shipping Promo Discount","Shipping Promo Discount Basis","Shipping Promo Tax",
	"Gift Wrap Promo Discount","Gift Wrap Promo Discount Basis","Gift Wrap Promo Tax","Tcs Cgst Rate",
	"Tcs Cgst Amount","Tcs Sgst Rate","Tcs Sgst Amount","Tcs Utgst Rate","Tcs Utgst Amount","Tcs Igst Rate",
	"Tcs Igst Amount","Warehouse Id","Fulfillment Channel","Payment Method Code","Credit Note No","Credit Note Date"
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
