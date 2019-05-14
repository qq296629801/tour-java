package cn.ymsys.api.dao.impl;

import cn.ymsys.api.dao.PhotoBase;
import cn.ymsys.api.common.enums.BaseEnum;
import cn.ymsys.api.common.model.BuPhoto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("BuUTenderPhotoDaoImpl")
public class BuUTenderPhotoBaseImpl extends PhotoBase {

	public BuUTenderPhotoBaseImpl(BaseEnum base) {
		super(base);
	}

	public BuUTenderPhotoBaseImpl() {
		this(BaseEnum.TENDER_IMG);
	}

	@Override
	public List<BuPhoto> findPhotos(int id) {
		return dao.find(
				"select * from bu_photo where ba_photo_type_id = " + this.getValue() + " and bu_object_id=" + id + "");
	}
}
