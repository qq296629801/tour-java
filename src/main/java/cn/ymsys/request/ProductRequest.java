package cn.ymsys.request;

import com.jyd.common.model.BuUProduct;

public class ProductRequest extends BuUProduct {
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
