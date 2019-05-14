package cn.ymsys.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.ymsys.api.common.model.BuPhoto;

import cn.ymsys.api.dao.PhotoBase;
import cn.ymsys.api.common.enums.BaseEnum;
@Component("CompanyPhotoDaoImpl")
public class CompanyPhotoBaseImpl extends PhotoBase {

	public CompanyPhotoBaseImpl(BaseEnum base) {
		super(base);
	}

	public CompanyPhotoBaseImpl() {
		this(BaseEnum.COMPANY_IMG);
	}

	@Override
	public List<BuPhoto> findPhotos(int id) {
		return dao.find(
				"select * from bu_photo where bu_photo_type_id=" + this.getValue() + " and bu_object_id=" + id + "");
	}

}
