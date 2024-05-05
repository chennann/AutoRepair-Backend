package com.chennann.car.service;



public interface IMemberLoginService {


    /**
     * 根据code，获取用户的openId
     * @param code
     * @return
     */
    public String getUserOpenId(String code);




}
