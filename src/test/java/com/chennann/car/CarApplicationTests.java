package com.chennann.car;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

@SpringBootTest
class CarApplicationTests {

    @Test
    void contextLoads() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        String string = list.toString();
        System.out.println(string);
    }

}
