package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBuCompanyPhoto<M extends BaseBuCompanyPhoto<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setBuCompanyId(java.lang.Integer buCompanyId) {
		set("bu_company_id", buCompanyId);
		return (M)this;
	}
	
	public java.lang.Integer getBuCompanyId() {
		return getInt("bu_company_id");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

}
