package cn.ymsys.api.service;

import cn.ymsys.api.common.model.Basics;
import cn.ymsys.api.common.model.BuPhoto;
import cn.ymsys.api.common.request.BuPhotoRequest;
import cn.ymsys.api.common.util.BasicsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoService {
    private BuPhoto dao = new BuPhoto();

    public List<BuPhoto> find() {
        return dao.find("select * from bu_photo");
    }


    public List<BuPhoto> findPhotos(int id, String key) {
        Basics basics = BasicsUtil.find(key);
        return dao.find(
                "select * from bu_photo where ba_photo_type_id = " + basics.getValuee() + " and bu_object_id=" + id + "");
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
