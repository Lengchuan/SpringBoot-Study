package com.lengchuan.springBoot.jpa.repository;

import com.lengchuan.springBoot.jpa.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-16
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

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
}
