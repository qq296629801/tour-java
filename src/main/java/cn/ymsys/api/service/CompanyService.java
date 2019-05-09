package cn.ymsys.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfinal.plugin.activerecord.Page;
import com.jyd.common.model.BuUCompany;

import cn.ymsys.common.request.CompanyRequest;
import cn.ymsys.common.util.Const;

@Service
public class CompanyService {

    private BuUCompany dao = new BuUCompany();

    public Page<BuUCompany> findCompanys(CompanyRequest vo) {
        String select = "select *";
        String from = "from bu_u_company";
        return dao.paginate(vo.getPage(), Const.PAGE_SIZE, select, from);
    }

    public BuUCompany findCompany(CompanyRequest vo) {
        return dao.findById(vo.getId());
    }

    @Transactional
    public BuUCompany save(CompanyRequest vo) {
        vo.setUpdateDate(new Date());
        vo.setUpdateUser("");
        dao._setAttrs(vo).save();
        return vo;
    }

    @Transactional
    public BuUCompany update(CompanyRequest vo) {
        vo.setUpdateDate(new Date());
        vo.setUpdateUser("");
        dao._setAttrs(vo).update();
        return vo;
    }

    @Transactional
    public BuUCompany delete(CompanyRequest vo) {
        dao._setAttrs(vo).delete();
        return vo;
    }

}
