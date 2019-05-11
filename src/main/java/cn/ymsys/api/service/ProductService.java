package cn.ymsys.api.service;

import cn.ymsys.api.common.request.ProductRequest;
import cn.ymsys.api.common.util.Const;
import cn.ymsys.api.common.util.OwnException;

import com.jfinal.plugin.activerecord.Page;
import cn.ymsys.api.common.model.BuUProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProductService {
	// 查询
	private BuUProduct dao = new BuUProduct();

	public Page<BuUProduct> findProducts(ProductRequest vo) {
		String select = "select * ";
		String from = "from bu_u_product";
		return dao.paginate(vo.getPage(), Const.PAGE_SIZE, select, from);
	}

	public BuUProduct findProduct(ProductRequest vo) {
		return dao.findById(vo.getId());
	}

	@Transactional
	public BuUProduct save(ProductRequest vo) throws OwnException {
		vo.setUpdateDate(new Date());
		vo.setUpdateUser("");
		dao._setAttrs(vo).save();
		return vo;
	}

	@Transactional
	public BuUProduct update(ProductRequest vo) throws OwnException {
		vo.setUpdateDate(new Date());
		vo.setUpdateUser("");
		dao._setAttrs(vo).update();
		return vo;
	}

	@Transactional
	public BuUProduct delete(ProductRequest vo) throws OwnException {
		dao._setAttrs(vo).delete();
		return vo;
	}
}
