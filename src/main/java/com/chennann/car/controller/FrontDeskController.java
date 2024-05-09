package com.chennann.car.controller;

import com.chennann.car.pojo.*;
import com.chennann.car.service.FrontDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/frontdesk")
public class FrontDeskController {

    @Autowired
    private FrontDeskService frontDeskService;

    //增加委托记录
    @PostMapping("/addFault")
    public Result addFault(@RequestBody Fault fault) {

        System.out.println(fault);

        List<Integer> specifics = fault.getItems();

        frontDeskService.addFault(fault);
        System.out.println(fault.getNumber());
        frontDeskService.addSpecifics(fault.getNumber(),  specifics);

        return Result.success();
    }


    //查看所有委托记录
    @GetMapping("/findFaults")
    public Result<PageBean<Fault>> findFaults (
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer number,
            @RequestParam(required = false) String identification_number,
            @RequestParam(required = false) String client_name,
            @RequestParam(required = false) Integer client_number

    ) {
        PageBean<Fault> cls = frontDeskService.findFaults(pageNum, pageSize, number, identification_number, client_name, client_number);
        return Result.success(cls);
    }

    //查看所有客户
    @GetMapping("/findClients")
    public Result<PageBean<Client>> findClients (
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String property,
            @RequestParam(required = false) String contact_number
    ) {
        PageBean<Client> cls = frontDeskService.findClients(pageNum, pageSize, number, name, property, contact_number);
        return Result.success(cls);
    }

    //查看所有车辆
    @GetMapping("/findCars")
    public Result<PageBean<Car>> findCars (
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String identification_number,
            @RequestParam(required = false) String license_plate_number,
            @RequestParam(required = false) String vehicle_type,
            @RequestParam(required = false) Integer client_number,
            @RequestParam(required = false) String client_name
    ) {
        PageBean<Car> cls = frontDeskService.findCars(pageNum, pageSize, identification_number, license_plate_number, vehicle_type, client_number, client_name);
        return Result.success(cls);
    }


    //查看所有维修人员
    @GetMapping("/findRepairman")
    public Result<PageBean<Repairman>> findRepairman (
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String jobs,
            @RequestParam(required = false) Integer number
    ) {
        PageBean<Repairman> cls = frontDeskService.findRepairman(pageNum, pageSize, jobs, number);
        return Result.success(cls);
    }

    //新增车辆
    @PostMapping("/addCar")
    public Result addCar(@RequestBody Car car) {
        System.out.println(car);

        //查找有没有这个人
        Client clt = frontDeskService.getClient(car.getClient_name(), car.getClient_contact_number());
        System.out.println(clt);

        if (clt == null) {
            //新增客户
            Client clt_tmp = new Client();
            clt_tmp.setName(car.getClient_name());
            clt_tmp.setProperty("个人");
            clt_tmp.setContact_number(car.getClient_contact_number());
            clt_tmp.setDiscount(1F);
            frontDeskService.addClient(clt_tmp);
            System.out.println(clt_tmp.getNumber());
            car.setClient_number(clt_tmp.getNumber());
        }
        else {
            car.setClient_number(clt.getNumber());
        }
        frontDeskService.addCar(car);
        return Result.success();
    }

    //新增客户
    @PostMapping("/addClient")
    public Result addClient(@RequestBody Client client) {
        frontDeskService.addClient(client);
        System.out.println(client.getNumber());
        return Result.success();
    }

    //查看故障表格（返回固定的表格数据）
    @GetMapping("/findSpecifics")
    public Result<PageBean<Specific>> findSpecifics (
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String jobs_type,
            @RequestParam(required = false) String keywords
    ) {
        PageBean<Specific> cls = frontDeskService.findSpecifics(pageNum, pageSize, jobs_type, keywords);
        return Result.success(cls);
    }
}
