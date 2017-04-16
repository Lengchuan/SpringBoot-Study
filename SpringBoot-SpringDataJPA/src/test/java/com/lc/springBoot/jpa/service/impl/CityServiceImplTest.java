package com.lc.springBoot.jpa.service.impl;

import com.BaseTest;
import com.lc.springBoot.jpa.entity.City;
import com.lc.springBoot.jpa.service.CityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-16
 */
public class CityServiceImplTest extends BaseTest{

    @Autowired
    private CityService cityService;
    @Test
    public void findById() throws Exception {
    }

    @Test
    public void findByCountryEquals() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findByNameContainingAndCountryContainingAllIgnoringCase() throws Exception {
    }

    @Test
    public void findByNameAndCountryAllIgnoringCase() throws Exception {
    }

    @Test
    public void createCity() throws Exception {
        City city = new City();
        city.setCountry("china");
        city.setMap("map");
        city.setName("beijing");
        city.setState("state");
        cityService.createCity(city);
    }

}