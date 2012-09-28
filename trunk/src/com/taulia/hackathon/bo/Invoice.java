package com.taulia.hackathon.bo;

import com.taulia.hackathon.rest.XmlStructureMapper;
import java.math.BigDecimal;
import java.util.Date;
import org.w3c.dom.Node;

/**
 * @author Matt Zeilenga
 */
public class Invoice {

  final String amount;
  final String apr;
  final String buyerName;
  final String currency;
  final String dueDate;
  final String id;
  final String invoiceDate;
  final String number;

  public Invoice(Node node) {
    this(new XmlStructureMapper(node));
  }

  public Invoice(XmlStructureMapper mapper) {
    this.amount = mapper.getFieldValue("amount");
    this.apr = mapper.getFieldValue("apr");
    this.buyerName = mapper.getFieldValue("buyer");
    this.currency = mapper.getFieldValue("currency");
    this.dueDate = mapper.getFieldValue("duedate");
    this.id = mapper.getFieldValue("invoice_id");
    this.invoiceDate = mapper.getFieldValue("invoicedate");
    this.number = mapper.getFieldValue("invoice_num");
  }

  public String getAmount() { return this.amount; }
  public String getAPR() { return this.apr; }
  public String getBuyerName() { return this.buyerName; }
  public String getCurrency() { return this.currency; }
  public String getDueDate() { return this.dueDate; }
  public String getId() { return this.id; }
  public String getInvoiceDate() { return this.invoiceDate; }
  public String getNumber() { return this.number; }

  @Override
  public String toString() {
    return this.number.concat(" - $").concat(this.amount);
  }
}
