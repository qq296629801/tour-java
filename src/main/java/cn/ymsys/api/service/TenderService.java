package cn.ymsys.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfinal.plugin.activerecord.Page;
import com.jyd.common.model.BuUTender;

import cn.ymsys.enums.ExceptionEnum;
import cn.ymsys.request.TenderRequest;
import cn.ymsys.util.Const;
import cn.ymsys.util.DataUtil;
import cn.ymsys.util.OwnException;

@Service
public class TenderService {

    private BuUTender dao = new BuUTender();

    public Page<BuUTender> findTenders(TenderRequest vo) {
        String select = "select * ";
        String from = "from bu_u_tender";
        return dao.paginate(vo.getPage(), Const.PAGE_SIZE, select, from);
    }

    public BuUTender findTender(TenderRequest vo) {
        return dao.findById(vo.getId());
    }

    @Transactional
    public BuUTender save(TenderRequest vo) throws OwnException {
        vo.setUpdateDate(new Date());
        dao._setAttrs(vo).save();
        return vo;
    }

    @Transactional
    public BuUTender update(TenderRequest vo) throws OwnException {
        vo.setUpdateDate(new Date());
        dao._setAttrs(vo).update();
        return vo;
    }

    @Transactional
    public BuUTender delete(TenderRequest vo) throws OwnException {
        dao._setAttrs(vo).delete();
        return vo;
    }
}
