package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fault {
    Integer number;
    String identification_number;
    String maintenance_type;
    String work_type;
    String billing_method;
    String signature;
    String damage_image;
    Double money;
    LocalDateTime date;

    List<Integer> items;




}
