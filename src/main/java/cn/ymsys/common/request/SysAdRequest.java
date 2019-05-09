package cn.ymsys.common.request;

import com.jyd.common.model.SysAd;

public class SysAdRequest extends SysAd {
	private int page;
	private String keyword;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
