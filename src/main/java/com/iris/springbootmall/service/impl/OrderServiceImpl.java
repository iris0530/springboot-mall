package com.iris.springbootmall.service.impl;

import com.iris.springbootmall.dao.OrderDao;
import com.iris.springbootmall.dao.ProductDao;
import com.iris.springbootmall.dto.BuyItem;
import com.iris.springbootmall.dto.CreateOrderRequest;
import com.iris.springbootmall.model.OrderItem;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        Integer totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            
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
}
