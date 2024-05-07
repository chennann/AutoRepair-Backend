package com.chennann.car.mapper;

import com.chennann.car.pojo.Fault;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClientMapper {


    List<Fault> findFaults(Integer clientNumber);
}
