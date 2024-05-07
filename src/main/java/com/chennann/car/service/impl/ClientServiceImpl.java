package com.chennann.car.service.impl;

import com.chennann.car.mapper.ClientMapper;
import com.chennann.car.pojo.Fault;
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
}
