package com.chennann.car.controller;

import com.chennann.car.pojo.*;
import com.chennann.car.service.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repairman")
public class RepairmanController {

    @Autowired
    private RepairmanService repairmanService;

    @Autowired
    private SendMsg sendMsg;

    //查看所有自己可以接受的工作
    @GetMapping("/findValidFaults")
    public Result<List<FaultDetail>> findValidFaults (Integer number) {

        List<FaultDetail> res = null;

        List<String> jobs = repairmanService.getJobsByNumber(number);
        for (String job : jobs) {
            List<FaultDetail> fds = repairmanService.findValidFaults(job);
            if (res == null) {
                res = new ArrayList<>(fds);
            } else {
                res.addAll(fds);
            }
        }

        return Result.success(res);
    }

    //查看自己的历史工作
    @GetMapping("/findHistoryFaults")
    public Result<List<FaultDetail>> findHistoryFaults (Integer number) {
        List<FaultDetail> res = repairmanService.findHistoryFaults(number);
        return Result.success(res);
    }

    //接受任务
    @PostMapping("/acceptTask")
    public Result acceptTask (Integer number_repm, Integer id) {
        repairmanService.acceptTask(number_repm, id);
        Integer fault_number = repairmanService.getFaultNumberById(id);
        moveOn(fault_number);
        return Result.success();
    }

    //完成任务
    @PostMapping("/finishTask")
//    public Result finishTask (Integer number_repm, Integer id) {
    public Result finishTask (@RequestBody TaskFinish taskFinish) {

        Integer fault_number = repairmanService.getFaultNumberById(taskFinish.getId());
        repairmanService.finishTask(taskFinish.getId());

        //计算零件
        List<Integer> componentsNumber = taskFinish.getComponents_number();
        for (Integer cNumber : componentsNumber) {
            repairmanService.useComponent(fault_number, cNumber);
        }
        moveOn(fault_number);
        return Result.success();
    }

    public void moveOn (Integer fault_number) {
        System.out.println(fault_number);

        //获取所有有repairman的waiting项目
        List<FaultDetail> fds = repairmanService.getItemsWithRepmAndWaiting(fault_number);
        for (FaultDetail fd : fds) {
            System.out.println(fd);
        }

        //获取正在做的数量
        Integer count = repairmanService.getCountWorking(fault_number);
        if (count == 0 && fds.size() != 0) {
            System.out.println("没有在做的");
            //分配一个做
            FaultDetail faultDetail = fds.get(0);
            repairmanService.setDoing(faultDetail);

            //发订单推进通知
            sendMsg.sendMsg(3);
        }
        else {
            if (fds.size() == 0) {
                System.out.println("做完了");

                //计算金额
                repairmanService.calculate(fault_number);

                //发提车通知
                sendMsg.sendMsg(1);

            }
        }
    }

    //查找所有零件
    @GetMapping("/findComponents")
    public Result<PageBean<Component>> findComponents (
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String vehicle_type,
            @RequestParam(required = false) Integer number

    ) {
        PageBean<Component> cls = repairmanService.findComponents(pageNum, pageSize, name, vehicle_type, number);
        return Result.success(cls);
    }
}
