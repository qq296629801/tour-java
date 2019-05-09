package cn.ymsys.api.dao.impl;

import cn.ymsys.api.dao.PhotoDao;
import cn.ymsys.common.enums.BaseEnum;
import cn.ymsys.common.model.BuPhoto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("BuUTenderPhotoDaoImpl")
public class BuUTenderPhotoDaoImpl extends PhotoDao {

	public BuUTenderPhotoDaoImpl(BaseEnum base) {
		super(base);
	}

	public BuUTenderPhotoDaoImpl() {
		this(BaseEnum.TENDER_IMG);
	}

	@Override
	public List<BuPhoto> findPhotos(int id) {
		return dao.find(
				"select * from bu_photo where ba_photo_type_id = " + this.getValue() + " and bu_object_id=" + id + "");
	}
}
