package com.chennann.car.service;

import com.chennann.car.pojo.*;

import java.util.List;

public interface FrontDeskService {
    void addFault(Fault fault);

    void addSpecifics(Integer number, List<Integer> specifics);

    PageBean<Client> findClients(Integer pageNum, Integer pageSize, String number, String name, String property, String contact_number);

    PageBean<Fault> findFaults(Integer pageNum, Integer pageSize, String number, String identification_number);

    PageBean<Car> findCars(Integer pageNum, Integer pageSize, String identificationNumber, String licensePlateNumber, String vehicleType);

    PageBean<Repairman> findRepairman(Integer pageNum, Integer pageSize, String jobs, Integer number);

    void addCar(Car car);

    Client getClient(String clientName, String clientContactNumber);

    void addClient(Client client);

    PageBean<Specific> findSpecifics(Integer pageNum, Integer pageSize, String jobsType, String keywords);
}
