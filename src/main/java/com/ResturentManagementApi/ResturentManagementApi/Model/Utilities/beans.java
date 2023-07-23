package com.ResturentManagementApi.ResturentManagementApi.Model.Utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Configuration
public class beans {
    @Bean

    public StringBuilder getBean(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rakesh@#12");
        return sb;
    }
}
