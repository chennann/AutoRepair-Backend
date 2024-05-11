package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecificsOfFaultNumber {

    Integer fault_number;
    Integer specific_number;
    Integer repairman_number;
    String states;
    String job_type;
    Double time;
    String name;
}
