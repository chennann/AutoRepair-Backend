package com.chennann.car.service.impl;

import com.chennann.car.mapper.RepairmanMapper;
import com.chennann.car.pojo.Component;
import com.chennann.car.pojo.FaultDetail;
import com.chennann.car.pojo.PageBean;
import com.chennann.car.service.RepairmanService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairmanServiceImpl implements RepairmanService {

    @Autowired
    private RepairmanMapper repairmanMapper;

    @Override
    public List<FaultDetail> findValidFaults(String job) {

        return repairmanMapper.findValidFaults(job);
    }

    @Override
    public List<String> getJobsByNumber(Integer number) {

        return repairmanMapper.getJobsByNumber(number);
    }

    @Override
    public List<FaultDetail> findHistoryFaults(Integer number) {

        return repairmanMapper.findHistoryFaults(number);
    }

    @Override
    public void acceptTask(Integer numberRepm, Integer id) {

        repairmanMapper.acceptTask(numberRepm, id);
    }

    @Override
    public Integer getFaultNumberById(Integer id) {

        return repairmanMapper.getFaultNumberById(id);
    }

    @Override
    public List<FaultDetail> getItemsWithRepmAndWaiting(Integer faultNumber) {

        return repairmanMapper.getItemsWithRepmAndWaiting(faultNumber);
    }

    @Override
    public Integer getCountWorking(Integer faultNumber) {

        return repairmanMapper.getCountWorking(faultNumber);
    }

    @Override
    public void setDoing(FaultDetail faultDetail) {
        repairmanMapper.setDoing(faultDetail);
    }

    @Override
    public void finishTask(Integer id) {

        repairmanMapper.finishTask(id);
    }

    @Override
    public PageBean<Component> findComponents(Integer pageNum, Integer pageSize, String name, String vehicleType, Integer number) {

        PageBean<Component> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);


        List<Component> as = repairmanMapper.findComponents(name, vehicleType, number);
        Page<Component> p = (Page<Component>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void useComponent(Integer faultNumber, Integer cNumber) {

        repairmanMapper.useComponent(faultNumber, cNumber);
    }

    @Override
    public void calculate(Integer faultNumber) {

        Double cPrice = repairmanMapper.calculateComponent(faultNumber);
        Double tPrice = repairmanMapper.calculateTime(faultNumber);
        System.out.println(cPrice);
        System.out.println(tPrice);

        Double totalPrice = cPrice + tPrice;

        //更新fault表
        System.out.println(totalPrice);
        repairmanMapper.updateMoney(totalPrice, faultNumber);
    }
}
