package com.customer.consumer.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERROR_LOG")
public class ErrorLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public ErrorLog() {
		// TODO Auto-generated constructor stub
	}

	public ErrorLog(String errorType, String errorDesc, String payLoad) {
		super();
		this.errorType = errorType;
		this.errorDesc = errorDesc;
		this.payLoad = payLoad;
	}

	@Column
	private String errorType;
	@Column
	private String errorDesc;
	@Column
	private String payLoad;

}