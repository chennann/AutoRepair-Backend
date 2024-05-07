package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Component {
    Integer number;
    String name;
    String vehicle_type;
    Double money;
}
