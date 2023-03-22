package com.richard.mintynconsumer.service;

import com.richard.mintynconsumer.model.Order;
import com.richard.mintynconsumer.model.OrderReport;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.DateUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderReportService {

    private List<Order> orders = new ArrayList<>();

    @KafkaListener(topics = "orders", groupId = "report-consumer")
    public void consume(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByDate(Date date) {
        return orders.stream()
                .filter(order -> order.getDate().equals(date))
                .collect(Collectors.toList());
    }
    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
        return orders.stream()
                .filter(order -> order.getDate().after(startDate) && order.getDate().before(endDate))
                .collect(Collectors.toList());
    }

    public List<OrderReport> getOrderReportByDateRange(Date startDate, Date endDate) {
        List<OrderReport> orderReports = new ArrayList<>();
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            List<Order> orders = getOrdersByDateRange(currentDate, currentDate);
            double totalOrderAmount = orders.stream().mapToDouble(order -> order.getPrice() * order.getQty()).sum();
            OrderReport orderReport = new OrderReport(currentDate, orders.size(), totalOrderAmount);
            orderReports.add(orderReport);
            currentDate = DateUtils.addDays(currentDate, 1); // increment date by 1 day
        }
        return orderReports;
    }




}
