package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskFinish {

    Integer number_repm;
    Integer id;

    List<Integer> components_number;
}
