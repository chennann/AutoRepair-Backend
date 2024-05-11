package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    String identification_number;
    String license_plate_number;
    String color;
    String vehicle_type;
    Integer client_number;

//    String client_name;
//    String client_contact_number;
}
