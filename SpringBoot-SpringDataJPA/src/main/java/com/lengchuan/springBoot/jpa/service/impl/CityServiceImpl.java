package com.lengchuan.springBoot.jpa.service.impl;

import com.lengchuan.springBoot.jpa.config.ReadAndWrite.annotation.TargetDataSource;
import com.lengchuan.springBoot.jpa.entity.City;
import com.lengchuan.springBoot.jpa.repository.CityRepository;
import com.lengchuan.springBoot.jpa.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-16
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    /**
     * 通过id查询
     * @param id
     * @return
     */
    public City findById(Long id) {
        return null;
    }

    /**
     * 通过country字段查询
     * @param country
     * @return
     */
    public City findByCountryEquals(String country) {
        return null;
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<City> findAll(Pageable pageable) {
        return null;
    }

    /**
     * 分页查询 name和country
     * @param name
     * @param country
     * @param pageable
     * @return
     */
    public Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable) {
        return null;
    }

    /**
     * 分页查询 name和country,忽略大小写
     * @param name
     * @param country
     * @return
     */
    public City findByNameAndCountryAllIgnoringCase(String name, String country) {
        return null;
    }

    @TargetDataSource(dataSource = "writeDruidDataSource")
    @Transactional(value = "writeTransactionManager")
    public City createCity(City city) {
        return cityRepository.save(city);
    }
}
