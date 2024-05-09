package com.chennann.car.controller;

import com.chennann.car.pojo.CurrentFault;
import com.chennann.car.pojo.Fault;
import com.chennann.car.pojo.Result;
import com.chennann.car.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //查看自己所有订单（fault表）
    @GetMapping("/findFaults")
    public Result<List<Fault>> findFaults (Integer client_number) {

        List<Fault> res = clientService.findFaults(client_number);

        return Result.success(res);
    }


    //查看当前订单详情
    @GetMapping("/getCurrentDetail")
    public Result<CurrentFault> getCurrentDetail (Integer client_number) {
        CurrentFault res = clientService.getCurrentDetail(client_number);
        return Result.success(res);
    }

}
