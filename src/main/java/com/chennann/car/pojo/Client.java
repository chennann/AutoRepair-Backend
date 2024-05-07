package com.chennann.car.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    Integer number;
    String name;
    String property;
    Float discount;
    String contact_person;
    String contact_number;
}
