package cn.ymsys.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.ymsys.api.common.model.BuPhoto;

import cn.ymsys.api.dao.PhotoDao;
import cn.ymsys.api.common.enums.BaseEnum;

@Component("BuUAuthenticationPhotoDaoImpl")
public class BuUAuthenticationPhotoDaoImpl extends PhotoDao {

	public BuUAuthenticationPhotoDaoImpl(BaseEnum base) {
		super(base);
	}

	public BuUAuthenticationPhotoDaoImpl() {
		this(BaseEnum.AUTHENTICATION_IMG);
	}

	@Override
	public List<BuPhoto> findPhotos(int id) {
		return dao.find(
				"select * from bu_photo where ba_photo_type_id = " + this.getValue() + " and bu_object_id=" + id + "");
	}

}
