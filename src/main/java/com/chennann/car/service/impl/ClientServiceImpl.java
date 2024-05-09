package com.chennann.car.service.impl;

import com.chennann.car.mapper.ClientMapper;
import com.chennann.car.pojo.Component;
import com.chennann.car.pojo.CurrentFault;
import com.chennann.car.pojo.Fault;
import com.chennann.car.pojo.Specific;
import com.chennann.car.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;


    @Override
    public List<Fault> findFaults(Integer clientNumber) {

        return clientMapper.findFaults(clientNumber);
    }

    @Override
    public CurrentFault getCurrentDetail(Integer clientNumber) {

        //从clientNumber查询目标faultNumber
        Integer faultNumber = clientMapper.getFaultNumberByClientNumber(clientNumber);
        System.out.println(faultNumber);

        CurrentFault res = new CurrentFault();


        //查询faultNumber对应的specifics
        List<Specific> res_specific = clientMapper.getSpecificsByFaultNumber(faultNumber);
        System.out.println(res_specific);

        //查询faultNumber对应的components
        List<Component> res_component = clientMapper.getComponentsByFaultNumber(faultNumber);
        System.out.println(res_component);

        //查询faultNumber对应的fault
        Fault res_fault = clientMapper.getFaultByFaultNumber(faultNumber);
        System.out.println(res_fault);

        res.setSpecifics(res_specific);
        res.setComponents(res_component);
        res.setFault(res_fault);

        return res;
    }
}
