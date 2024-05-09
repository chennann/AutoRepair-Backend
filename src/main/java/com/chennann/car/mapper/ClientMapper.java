package com.chennann.car.mapper;

import com.chennann.car.pojo.Component;
import com.chennann.car.pojo.Fault;
import com.chennann.car.pojo.Specific;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClientMapper {


    List<Fault> findFaults(Integer clientNumber);

    Integer getFaultNumberByClientNumber(Integer clientNumber);

    List<Specific> getSpecificsByFaultNumber(Integer faultNumber);

    List<Component> getComponentsByFaultNumber(Integer faultNumber);

    Fault getFaultByFaultNumber(Integer faultNumber);
}
