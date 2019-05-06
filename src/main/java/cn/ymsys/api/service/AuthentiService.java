package cn.ymsys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfinal.plugin.activerecord.Page;
import com.jyd.common.model.BuUAuthentication;

import cn.ymsys.request.AuthentiRequest;
import cn.ymsys.util.Const;

@Service
public class AuthentiService {
	private BuUAuthentication dao = new BuUAuthentication();

	public List<BuUAuthentication> find(AuthentiRequest vo) {
		return dao.find("select * from bu_u_authentication where sys_user_id=" + vo.getSysUserId() + "");
	}

	public Page<BuUAuthentication> findByPage(AuthentiRequest vo) {
		return dao.paginate(vo.getPage(), Const.PAGE_SIZE, "select *", "from bu_u_authentication");
	}

	@Transactional
	public BuUAuthentication save(AuthentiRequest vo) {
		dao._setAttrs(vo).save();
		return vo;
	}

	@Transactional
	public BuUAuthentication delete(AuthentiRequest vo) {
		dao._setAttrs(vo).delete();
		return vo;
	}

	@Transactional
	public BuUAuthentication update(AuthentiRequest vo) {
		dao._setAttrs(vo).update();
		return vo;
	}
}
