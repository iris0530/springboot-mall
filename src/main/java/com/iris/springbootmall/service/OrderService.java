package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.CreateOrderRequest;
import com.iris.springbootmall.dto.OrderQueryParams;
import com.iris.springbootmall.model.Order;
import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

}
