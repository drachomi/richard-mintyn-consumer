package com.richard.mintynconsumer.controller;

import com.richard.mintynconsumer.model.Order;
import com.richard.mintynconsumer.model.OrderReport;
import com.richard.mintynconsumer.service.OrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
public class OrderReportController {

    @Autowired
    private OrderReportService orderReportService;



    @GetMapping("/report")
    public List<OrderReport> getOrdersByDateRange(
            @RequestParam(name = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {


        return orderReportService.getOrderReportByDateRange(startDate, endDate);
    }

}
