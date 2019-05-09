package cn.ymsys.common.request;

import cn.ymsys.common.model.BuPhoto;

public class BuPhotoRequest extends BuPhoto {
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
