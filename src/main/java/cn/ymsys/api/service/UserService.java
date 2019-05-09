package cn.ymsys.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyd.common.model.SysUser;

import cn.ymsys.common.request.UserRequest;

@Service
public class UserService {
	private SysUser dao = new SysUser();

	public List<SysUser> findUsers(UserRequest vo) {
		return dao.find("select * from sys_user");
	}

	public SysUser findByUUid(String uuid) {
		return dao.findFirst("select * from sys_user where open_id=" + uuid + "");
	}

	@Transactional
	public UserRequest save(UserRequest vo) {
		vo.setUpdateDate(new Date());
		dao._setAttrs(vo).save();
		return vo;
	}

	@Transactional
	public SysUser delete(UserRequest vo) {
		vo.setUpdateDate(new Date());
		dao._setAttrs(vo).delete();
		return vo;
	}

	@Transactional
	public SysUser update(UserRequest vo) {
		vo.setUpdateDate(new Date());
		dao._setAttrs(vo).update();
		return vo;
	}

}
