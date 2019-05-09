package cn.ymsys.common.request;

import com.jyd.common.model.BuUCompetitiveTender;

public class CompetiRequest extends BuUCompetitiveTender {
	private String keyword;
	private int page;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
