package cn.ymsys.common.request;

import cn.ymsys.common.model.BuUAuthentication;

public class AuthentiRequest extends BuUAuthentication {

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
