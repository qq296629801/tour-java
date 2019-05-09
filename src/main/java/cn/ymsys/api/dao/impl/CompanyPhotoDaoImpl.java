package cn.ymsys.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jyd.common.model.BuPhoto;

import cn.ymsys.api.dao.PhotoDao;
import cn.ymsys.common.enums.BaseEnum;
@Component("CompanyPhotoDaoImpl")
public class CompanyPhotoDaoImpl extends PhotoDao {

	public CompanyPhotoDaoImpl(BaseEnum base) {
		super(base);
	}

	public CompanyPhotoDaoImpl() {
		this(BaseEnum.COMPANY_IMG);
	}

	@Override
	public List<BuPhoto> findPhotos(int id) {
		return dao.find(
				"select * from bu_photo where bu_photo_type_id=" + this.getValue() + " and bu_object_id=" + id + "");
	}

}
