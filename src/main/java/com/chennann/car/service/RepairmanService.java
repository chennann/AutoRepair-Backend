package com.chennann.car.service;

import com.chennann.car.pojo.Component;
import com.chennann.car.pojo.FaultDetail;
import com.chennann.car.pojo.PageBean;

import java.util.List;

public interface RepairmanService {
    List<FaultDetail> findValidFaults(String job);

    List<String> getJobsByNumber(Integer number);

    List<FaultDetail> findHistoryFaults(Integer number);

    void acceptTask(Integer numberRepm, Integer id);

    Integer getFaultNumberById(Integer id);

    List<FaultDetail> getItemsWithRepmAndWaiting(Integer faultNumber);

    Integer getCountWorking(Integer faultNumber);

    void setDoing(FaultDetail faultDetail);

    void finishTask(Integer id);

    PageBean<Component> findComponents(Integer pageNum, Integer pageSize, String name, String vehicleType, Integer number);

    void useComponent(Integer faultNumber, Integer cNumber);

    Double calculate(Integer faultNumber);
}
