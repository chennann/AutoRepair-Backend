package com.chennann.car.service;

import com.chennann.car.pojo.CurrentFault;
import com.chennann.car.pojo.Fault;

import java.util.List;

public interface ClientService {
    List<Fault> findFaults(Integer clientNumber);

    CurrentFault getCurrentDetail(Integer clientNumber);
}
