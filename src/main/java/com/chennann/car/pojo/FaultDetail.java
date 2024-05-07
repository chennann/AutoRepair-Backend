package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FaultDetail {
    String identification_number;
    Integer fault_number;
    Integer specific_number;
    Double time;
    String name;
    String states;
    Integer id;
}
