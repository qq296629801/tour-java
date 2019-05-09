package cn.ymsys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyd.common.model.SysAd;

import cn.ymsys.common.request.SysAdRequest;

@Service
public class AdService {
	private SysAd dao = new SysAd();

	public List<SysAd> find(SysAdRequest vo) {
		return dao.find("select * from sys_ad where ba_postion_type_id=" + vo.getBaPostionTypeId() + "");
	}

	@Transactional
	public SysAd save(SysAdRequest vo) {
		dao._setAttrs(vo).save();
		return vo;
	}

	@Transactional
	public SysAd update(SysAdRequest vo) {
		dao._setAttrs(vo).update();
		return vo;
	}

	@Transactional
	public SysAd delete(SysAdRequest vo) {
		dao._setAttrs(vo).delete();
		return vo;
	}

}
