package com.richard.mintynconsumer.service;

import com.richard.mintynconsumer.model.Order;
import com.richard.mintynconsumer.model.OrderReport;
import com.richard.mintynconsumer.model.Product;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestPropertySource(properties = {"spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
public class OrderReportServiceTest {

    private OrderReportService orderReportService;

    @BeforeEach
    void setUp() {
        orderReportService = new OrderReportService();
    }

    @Test
    public void testGetOrderReportByDateRange() {
        Date today = new Date();
        Date yesterday = DateUtils.addDays(today, -1);
        Date tomorrow = DateUtils.addDays(today, 1);
        Product product = new Product(UUID.randomUUID(), "Product 1", "Product 1 Description", 10.0, 100);


        // Create some orders for yesterday, today, and tomorrow
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(UUID.randomUUID(), "Alice", "555-1234", 1.99, 3,
                product, yesterday));
        orders.add(new Order(UUID.randomUUID(), "Bob", "555-5678", 4.99, 1,
                product, today));
        orders.add(new Order(UUID.randomUUID(), "Charlie", "555-9876", 3.49, 2,
                product, tomorrow));

        // Add the orders to the service
        for (Order order : orders) {
            orderReportService.consume(order);
        }

        // Get the order report for yesterday and today
        List<OrderReport> orderReports = orderReportService.getOrderReportByDateRange(yesterday, today);

        // Verify the order report
        assertEquals(2, orderReports.size());

        OrderReport yesterdayReport = orderReports.get(0);
        assertEquals(yesterday, yesterdayReport.getDate());
        assertEquals(5.97, yesterdayReport.getTotalAmount(), 0.001);

        OrderReport todayReport = orderReports.get(1);
        assertEquals(today, todayReport.getDate());
        assertEquals(4.99, todayReport.getTotalAmount(), 0.001);
    }
}
