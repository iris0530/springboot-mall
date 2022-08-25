package com.iris.springbootmall.service.impl;

import com.iris.springbootmall.dao.OrderDao;
import com.iris.springbootmall.dao.ProductDao;
import com.iris.springbootmall.dao.UserDao;
import com.iris.springbootmall.dto.BuyItem;
import com.iris.springbootmall.dto.CreateOrderRequest;
import com.iris.springbootmall.dto.OrderQueryParams;
import com.iris.springbootmall.model.Order;
import com.iris.springbootmall.model.OrderItem;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.model.User;
import com.iris.springbootmall.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OrderServiceImpl implements OrderService {
    
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
       // 檢查 user 是否存在
        User user = userDao.getUserById(userId);
        
        if(null == user) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Integer totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            
            // 檢查 product 是否存在、庫存是否足夠
            if(null == product) {
                log.warn("商品ID {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}", product.getProductName(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            
            // 扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());
             
            // 計算總價錢
            Integer amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;
            
            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            
            orderItemList.add(orderItem);
        }
        
        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);
        
        orderDao.createOrderItems(orderId, orderItemList);
        
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        
        order.setOrderItemList(orderItemList);
        
        return order;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);
        
        for(Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }
}
