package cn.ymsys.api.common.request;

import cn.ymsys.api.common.model.BuUProduct;

public class ProductRequest extends BuUProduct {
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
