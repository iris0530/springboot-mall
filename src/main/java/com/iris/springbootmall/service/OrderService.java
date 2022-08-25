package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.CreateOrderRequest;
import com.iris.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

}
