package com.lc.springBoot.jpa.service;

import com.lc.springBoot.jpa.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-16
 */
public interface CityService {

    /**
     * 通过id查询
     * @param id
     * @return
     */
    City findById(Long id);

    /**
     * 通过country字段查询
     * @param country
     * @return
     */
    City findByCountryEquals(String country);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<City> findAll(Pageable pageable);

    /**
     * 分页查询 name和country
     * @param name
     * @param country
     * @param pageable
     * @return
     */
    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
                                                                       String country, Pageable pageable);

    /**
     * 分页查询 name和country,忽略大小写
     * @param name
     * @param country
     * @return
     */
    City findByNameAndCountryAllIgnoringCase(String name, String country);

    /**
     * 创建
     * @param city
     * @return
     */
    City createCity(City city);
}
