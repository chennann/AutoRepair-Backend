package com.chennann.car.service.impl;

import com.chennann.car.mapper.FrontDeskMapper;
import com.chennann.car.pojo.*;
import com.chennann.car.service.FrontDeskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class FrontDeskServiceImpl implements FrontDeskService {

    @Autowired
    private FrontDeskMapper frontDeskMapper;
    private Fault fault;


    @Override
    public void addFault(Fault fault) {
        this.fault = fault;
        frontDeskMapper.addFault(fault);
    }

    @Override
    public void addSpecifics(Integer number, List<Integer> specifics) {
        frontDeskMapper.addSpecifics(number, specifics);
    }

    @Override
    public PageBean<Client> findClients(Integer pageNum, Integer pageSize, String number, String name, String property, String contact_number) {

        PageBean<Client> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);


        List<Client> as = frontDeskMapper.findClients(number, name, property, contact_number);
        Page<Client> p = (Page<Client>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Fault> findFaults(Integer pageNum, Integer pageSize, String number, String identification_number) {

        PageBean<Fault> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);


        List<Fault> as = frontDeskMapper.findFaults(number, identification_number);
        Page<Fault> p = (Page<Fault>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Car> findCars(Integer pageNum, Integer pageSize, String identificationNumber, String licensePlateNumber, String vehicleType) {

        PageBean<Car> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);


        List<Car> as = frontDeskMapper.findCars(identificationNumber, licensePlateNumber, vehicleType);
        Page<Car> p = (Page<Car>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Repairman> findRepairman(Integer pageNum, Integer pageSize, String jobs, Integer number) {

        PageBean<Repairman> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);


        List<Repairman> as = frontDeskMapper.findRepairman(jobs, number);
        Page<Repairman> p = (Page<Repairman>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void addCar(Car car) {
        frontDeskMapper.addCar(car);
    }

    @Override
    public Client getClient(String clientName, String clientContactNumber) {

        return frontDeskMapper.getClient(clientName, clientContactNumber);
    }

    @Override
    public void addClient(Client client) {
        frontDeskMapper.addClient(client);
    }

    @Override
    public PageBean<Specific> findSpecifics(Integer pageNum, Integer pageSize, String jobsType, String keywords) {

        PageBean<Specific> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);


        List<Specific> as = frontDeskMapper.findSpecifics(jobsType, keywords);
        Page<Specific> p = (Page<Specific>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
