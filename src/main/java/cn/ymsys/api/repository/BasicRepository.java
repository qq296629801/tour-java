package cn.ymsys.api.repository;

import cn.ymsys.api.model.Basic;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BasicRepository extends PagingAndSortingRepository<Basic, String> {
    //分页查询
    Page<Basic> findAll(SpringDataWebProperties.Pageable pageable);

    void add(Basic basic);
}
