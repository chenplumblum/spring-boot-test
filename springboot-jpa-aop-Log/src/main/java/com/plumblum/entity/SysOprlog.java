package com.plumblum.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the sys_oprlog database table.
 * 
 */
@Entity
@Table(name="sys_oprlog")
@NamedQuery(name="SysOprlog.findAll", query="SELECT s FROM SysOprlog s")
public class SysOprlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OPRLOG_ID")
	private String oprlogId;

	@Column(name="OPRLOG_DESCRIPTION")
	private String oprlogDescription;

	@Column(name="OPRLOG_LOGIN_IP")
	private String oprlogLoginIp;

	@Column(name="OPRLOG_MODULE_NAME")
	private String oprlogModuleName;

	@Column(name="OPRLOG_PARAM")
	private String oprlogParam;

	@Column(name="OPRLOG_REMARK")
	private String oprlogRemark;

	@Column(name="OPRLOG_STATUS")
	private String oprlogStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPRLOG_TIME")
	private Date oprlogTime;

	@Column(name="OPRLOG_TYPE")
	private String oprlogType;

	@Column(name="OPRLOG_USER_ACCOUNT")
	private String oprlogUserAccount;

	@Column(name="OPRLOG_USER_ID")
	private String oprlogUserId;

	@Column(name="LOGIN_DEPT_ID")
	private String loginDeptId;

	public String getLoginDeptId() {
		return loginDeptId;
	}

	public void setLoginDeptId(String loginDeptId) {
		this.loginDeptId = loginDeptId;
	}

	@Column(name="OPRLOG_USER_NAME")
	private String oprlogUserName;

	public SysOprlog() {
	}

	public String getOprlogId() {
		return this.oprlogId;
	}

	public void setOprlogId(String oprlogId) {
		this.oprlogId = oprlogId;
	}

	public String getOprlogDescription() {
		return this.oprlogDescription;
	}

	public void setOprlogDescription(String oprlogDescription) {
		this.oprlogDescription = oprlogDescription;
	}

	public String getOprlogLoginIp() {
		return this.oprlogLoginIp;
	}

	public void setOprlogLoginIp(String oprlogLoginIp) {
		this.oprlogLoginIp = oprlogLoginIp;
	}

	public String getOprlogModuleName() {
		return this.oprlogModuleName;
	}

	public void setOprlogModuleName(String oprlogModuleName) {
		this.oprlogModuleName = oprlogModuleName;
	}

	public String getOprlogParam() {
		return this.oprlogParam;
	}

	public void setOprlogParam(String oprlogParam) {
		this.oprlogParam = oprlogParam;
	}

	public String getOprlogRemark() {
		return this.oprlogRemark;
	}

	public void setOprlogRemark(String oprlogRemark) {
		this.oprlogRemark = oprlogRemark;
	}

	public String getOprlogStatus() {
		return this.oprlogStatus;
	}

	public void setOprlogStatus(String oprlogStatus) {
		this.oprlogStatus = oprlogStatus;
	}

	public Date getOprlogTime() {
		return this.oprlogTime;
	}

	public void setOprlogTime(Date oprlogTime) {
		this.oprlogTime = oprlogTime;
	}

	public String getOprlogType() {
		return this.oprlogType;
	}

	public void setOprlogType(String oprlogType) {
		this.oprlogType = oprlogType;
	}

	public String getOprlogUserAccount() {
		return this.oprlogUserAccount;
	}

	public void setOprlogUserAccount(String oprlogUserAccount) {
		this.oprlogUserAccount = oprlogUserAccount;
	}

	public String getOprlogUserId() {
		return this.oprlogUserId;
	}

	public void setOprlogUserId(String oprlogUserId) {
		this.oprlogUserId = oprlogUserId;
	}

	public String getOprlogUserName() {
		return this.oprlogUserName;
	}

	public void setOprlogUserName(String oprlogUserName) {
		this.oprlogUserName = oprlogUserName;
	}

}