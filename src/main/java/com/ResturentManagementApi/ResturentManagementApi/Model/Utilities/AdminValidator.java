package com.ResturentManagementApi.ResturentManagementApi.Model.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class AdminValidator {
    @Autowired
    StringBuilder sb;

    public String get(){
        return sb.toString();
    }
}
