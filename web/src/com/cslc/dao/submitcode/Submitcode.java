package com.cslc.dao.submitcode;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Submitcode extends BaseEntity {

	public static final byte STATUS_UNUSED = 0;// 未使用
	public static final byte STATUS_USED = 1;// 已使用
	

	


	public static final int CATEGORY_SEND_SMS = 0;// 发送短信按钮
	public static final int CATEGORY_REGIST = 1;// 注册提交按钮
	public static final byte CATEGORY_FIND_PASSWORD = 2;// 找回密码

	private Long accountid;
	private Date createtime;
	private String code;
	private String ip;
	private String mobile;
	private Long id;
	private String terminalid;
	private Integer category;
	private Byte status;

	public Long getAccountid(){
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIp(){
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTerminalid(){
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public Integer getCategory(){
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}