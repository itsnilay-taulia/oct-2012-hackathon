package com.taulia.hackathon.bo;

import com.taulia.hackathon.rest.XmlStructureMapper;
import java.math.BigDecimal;
import java.util.Date;
import org.w3c.dom.Node;

/**
 * @author Matt Zeilenga
 */
public class Invoice {

  final BigDecimal amount;
  final BigDecimal apr;
  final String buyerName;
  final String currency;
  final Date dueDate;
  final String id;
  final Date invoiceDate;
  final String number;

  public Invoice(Node node) {
    this(new XmlStructureMapper(node));
  }

  public Invoice(XmlStructureMapper mapper) {
    this.amount = mapper.getFieldValueAsBigDecimal("amount");
    this.apr = mapper.getFieldValueAsBigDecimal("apr");
    this.buyerName = mapper.getFieldValueAsString("buyer");
    this.currency = mapper.getFieldValueAsString("currency");
    this.dueDate = mapper.getFieldValueAsDate("duedate");
    this.id = mapper.getFieldValueAsString("invoice_id");
    this.invoiceDate = mapper.getFieldValueAsDate("invoicedate");
    this.number = mapper.getFieldValueAsString("invoice_num");
  }

  public BigDecimal getAmount() { return this.amount; }
  public BigDecimal getAPR() { return this.apr; }
  public String getBuyerName() { return this.buyerName; }
  public String getCurrency() { return this.currency; }
  public Date getDueDate() { return this.dueDate; }
  public String getId() { return this.id; }
  public Date getInvoiceDate() { return this.invoiceDate; }
  public String getNumber() { return this.number; }
}
