package cn.ymsys.api.repository;

import cn.ymsys.api.model.Basic;

import java.util.List;

public interface BasicRepository {
    List<Basic> findALl();

    void add(Basic basic);
}
