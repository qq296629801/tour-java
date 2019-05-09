package cn.ymsys.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfinal.plugin.activerecord.Page;
import cn.ymsys.common.model.BuUCompetitiveTender;

import cn.ymsys.common.request.CompetiRequest;
import cn.ymsys.common.util.Const;

@Service
public class CompetiTenderService {
	private BuUCompetitiveTender dao = new BuUCompetitiveTender();

	public List<BuUCompetitiveTender> find(CompetiRequest vo) {
		return dao.find("select * from bu_u_competitive_tender where bu_tender_id=" + vo.getBuTenderId() + "");
	}

	public Page<BuUCompetitiveTender> findByPage(CompetiRequest vo) {
		return dao.paginate(vo.getPage(), Const.PAGE_SIZE, "select *", "CompetiRequest vo");
	}

	@Transactional
	public BuUCompetitiveTender save(CompetiRequest vo) {
		vo.setUpdateDate(new Date());
		dao._setAttrs(vo).save();
		return vo;
	}

	@Transactional
	public BuUCompetitiveTender update(CompetiRequest vo) {
		vo.setUpdateDate(new Date());
		dao._setAttrs(vo).update();
		return vo;
	}

	@Transactional
	public BuUCompetitiveTender delete(CompetiRequest vo) {
		dao._setAttrs(vo).delete();
		return vo;
	}
}
