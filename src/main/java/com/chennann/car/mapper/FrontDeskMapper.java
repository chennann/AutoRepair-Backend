package com.chennann.car.mapper;

import com.chennann.car.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FrontDeskMapper {

    @Insert("insert into fault (identification_number, maintenance_type, work_type, billing_method, signature, damage_image) values " +
            "(#{identification_number}, #{maintenance_type}, #{work_type}, #{billing_method}, #{signature}, #{damage_image})")
    @Options(useGeneratedKeys = true, keyProperty = "number")
    void addFault(Fault fault);

    void addSpecifics(Integer number, List<Integer> specifics);

    List<Client> findClients(String number, String name, String property, String contact_number);

    List<Fault> findFaults(Integer number, String identification_number, String client_name, Integer client_number);

    List<Car> findCars(String identificationNumber, String licensePlateNumber, String vehicleType, Integer clientNumber, String clientName);

    List<Repairman> findRepairman(String jobs, Integer number);

    @Insert("insert into car (identification_number, license_plate_number, color, vehicle_type, client_number) values " +
            "(#{identification_number}, #{license_plate_number}, #{color}, #{vehicle_type}, #{client_number})")
    void addCar(Car car);

    @Select("select * from client where name = #{clientName} and contact_number = #{clientContactNumber}")
    Client getClient(String clientName, String clientContactNumber);

    @Insert("insert into client (name, property, discount, contact_person, contact_number) values (#{name}, #{property}, #{discount}, #{contact_person}, #{contact_number})")
    @Options(useGeneratedKeys = true, keyProperty = "number")
    void addClient(Client client);

    List<Specific> findSpecifics(String jobsType, String keywords);

    @Delete("delete from car where identification_number = #{identificationNumber}")
    void deleteCarByIdentificationNumber(String identificationNumber);

    @Delete("delete from car where license_plate_number = #{licensePlateNumber}")
    void deleteCarByLicensePlateNumber(String licensePlateNumber);

    List<SpecificsOfFaultNumber> getSpecificsByFaultNumber(Integer faultNumber);
}
