package cn.ymsys.api.common.request;

import cn.ymsys.api.common.model.BuUAuthentication;

public class AuthentiRequest extends BuUAuthentication {

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
