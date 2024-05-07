package com.chennann.car.mapper;

import com.chennann.car.pojo.Component;
import com.chennann.car.pojo.FaultDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RepairmanMapper {

    List<FaultDetail> findValidFaults(String job);

    @Select("select jobs from repairman where number = #{number}")
    List<String> getJobsByNumber(Integer number);

    List<FaultDetail> findHistoryFaults(Integer number);

    @Update("update fault_specific set repairman_number = #{numberRepm} where number = #{id}")
    void acceptTask(Integer numberRepm, Integer id);

    @Select("select fault_number from fault_specific where number = #{id}")
    Integer getFaultNumberById(Integer id);

    List<FaultDetail> getItemsWithRepmAndWaiting(Integer faultNumber);

    Integer getCountWorking(Integer faultNumber);

    @Update("update fault_specific set states = 'Doing' where number = #{id}")
    void setDoing(FaultDetail faultDetail);

    @Update("update fault_specific set states = 'Done' where number = #{id}")
    void finishTask(Integer id);

    List<Component> findComponents(String name, String vehicleType, Integer number);

    @Insert("insert into component_project (fault_number, component_number) values (#{faultNumber}, #{cNumber})")
    void useComponent(Integer faultNumber, Integer cNumber);

    Double calculateComponent(Integer faultNumber);

    Double calculateTime(Integer faultNumber);

    @Update("update fault set money = #{totalPrice} where number = #{faultNumber}")
    void updateMoney(Double totalPrice, Integer faultNumber);
}
