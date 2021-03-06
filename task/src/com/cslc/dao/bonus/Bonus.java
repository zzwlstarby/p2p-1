package com.cslc.dao.bonus;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Bonus extends BaseEntity {

	public static final byte STATUS_UNUSED = 0;// 未使用
	public static final byte STATUS_USING = 1;// 返现中
	public static final byte STATUS_USED = 2;// 已返现
	public static final byte STATUS_EXPIRED = 3;// 已过期
	
	private Date createtime;
	private Double totalbonus;
	private Integer channel;
	private Date endtime;
	private Date starttime;
	private String title;
	private Long selfitemid;
	private Date backtime;
	private Date usetime;
	private Double usebonus;
	private Long accountid;
	private Integer rate;
	private Long id;
	private Byte status;

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Double getTotalbonus(){
		return totalbonus;
	}

	public void setTotalbonus(Double totalbonus) {
		this.totalbonus = totalbonus;
	}

	public Integer getChannel(){
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Date getEndtime(){
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getStarttime(){
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSelfitemid(){
		return selfitemid;
	}

	public void setSelfitemid(Long selfitemid) {
		this.selfitemid = selfitemid;
	}

	public Date getBacktime(){
		return backtime;
	}

	public void setBacktime(Date backtime) {
		this.backtime = backtime;
	}

	public Date getUsetime(){
		return usetime;
	}

	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}

	public Double getUsebonus(){
		return usebonus;
	}

	public void setUsebonus(Double usebonus) {
		this.usebonus = usebonus;
	}

	public Long getAccountid(){
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Integer getRate(){
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}