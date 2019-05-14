package cn.ymsys.api.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ymsys.api.common.model.BuPhoto;

import cn.ymsys.api.dao.PhotoBase;
import cn.ymsys.api.common.request.BuPhotoRequest;

@Service
public class PhotoService {
	private BuPhoto dao = new BuPhoto();

	@Autowired
	private final Map<String, PhotoBase> photoMap = new ConcurrentHashMap<>();

	public List<BuPhoto> find() {
		return dao.find("select * from bu_photo");
	}

	public List<BuPhoto> get(String api, int id) {
		return photoMap.get(api).findPhotos(id);
	}

	@Transactional
	public BuPhoto save(BuPhotoRequest vo) {
		dao._setAttrs(vo).save();
		return vo;
	}

	@Transactional
	public BuPhoto update(BuPhotoRequest vo) {
		dao._setAttrs(vo).update();
		return vo;
	}

	@Transactional
	public BuPhoto delete(BuPhotoRequest vo) {
		dao._setAttrs(vo).delete();
		return vo;
	}
}
