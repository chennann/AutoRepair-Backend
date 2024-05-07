package com.chennann.car.service;

import com.chennann.car.pojo.Fault;

import java.util.List;

public interface ClientService {
    List<Fault> findFaults(Integer clientNumber);
}
