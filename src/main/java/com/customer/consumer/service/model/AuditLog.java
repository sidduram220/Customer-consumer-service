package com.customer.consumer.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDIT_LOG")
public class AuditLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  public AuditLog(String customerNumber, String payLoad) {
    super();
    this.customerNumber = customerNumber;
    this.payLoad = payLoad;
  }

  @Column
  private String customerNumber;
  @Column
  private String payLoad;

}
