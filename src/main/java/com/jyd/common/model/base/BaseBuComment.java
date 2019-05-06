package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBuComment<M extends BaseBuComment<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setSysUserId(java.lang.Integer sysUserId) {
		set("sys_user_id", sysUserId);
		return (M)this;
	}
	
	public java.lang.Integer getSysUserId() {
		return getInt("sys_user_id");
	}

	public M setScore(java.lang.Integer score) {
		set("score", score);
		return (M)this;
	}
	
	public java.lang.Integer getScore() {
		return getInt("score");
	}

	public M setCompanyId(java.lang.Integer companyId) {
		set("company_id", companyId);
		return (M)this;
	}
	
	public java.lang.Integer getCompanyId() {
		return getInt("company_id");
	}

	public M setCommnet(java.lang.String commnet) {
		set("commnet", commnet);
		return (M)this;
	}
	
	public java.lang.String getCommnet() {
		return getStr("commnet");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

}
