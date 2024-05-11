package com.chennann.car.service;

import com.chennann.car.pojo.*;

import java.util.List;

public interface FrontDeskService {
    void addFault(Fault fault);

    void addSpecifics(Integer number, List<Integer> specifics);

    PageBean<Client> findClients(Integer pageNum, Integer pageSize, String number, String name, String property, String contact_number);

    PageBean<Fault> findFaults(Integer pageNum, Integer pageSize, Integer number, String identification_number, String client_name, Integer client_number);

    PageBean<Car> findCars(Integer pageNum, Integer pageSize, String identificationNumber, String licensePlateNumber, String vehicleType, Integer clientNumber, String clientName);

    PageBean<Repairman> findRepairman(Integer pageNum, Integer pageSize, String jobs, Integer number);

    void addCar(Car car);

    Client getClient(String clientName, String clientContactNumber);

    void addClient(Client client);

    PageBean<Specific> findSpecifics(Integer pageNum, Integer pageSize, String jobsType, String keywords);

    void deleteCarByIdentificationNumber(String identificationNumber);

    void deleteCarByLicensePlateNumber(String licensePlateNumber);

    List<SpecificsOfFaultNumber> getSpecificsByFaultNumber(Integer faultNumber);
}
