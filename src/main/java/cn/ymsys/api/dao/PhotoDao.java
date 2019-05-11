package cn.ymsys.api.dao;

import java.util.List;

import cn.ymsys.api.common.model.BuPhoto;

import cn.ymsys.api.common.enums.BaseEnum;

public abstract class PhotoDao {
	public abstract List<BuPhoto> findPhotos(int id);

	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public BuPhoto dao = new BuPhoto();

	public PhotoDao(BaseEnum base) {
		this.value = base.getValue();
	}
}
