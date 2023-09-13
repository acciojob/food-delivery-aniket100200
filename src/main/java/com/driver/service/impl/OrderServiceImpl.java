package com.driver.service.impl;

import com.driver.Transformers.OrderTransformer;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order)
    {
        OrderEntity orderEntity=new OrderEntity();

        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setCost(order.getCost());
       orderEntity.setItems(order.getItems());
       orderEntity.setStatus(true);

       orderEntity=orderRepository.save(orderEntity);

       order.setId(orderEntity.getId());
       order.setStatus(orderEntity.isStatus());
       order.setUserId(orderEntity.getUserId());
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception
    {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);

        OrderDto orderDto=OrderTransformer.orderDtoFromOrderEntity(orderEntity);
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception
    {

        OrderEntity orderEntity= orderRepository.findByOrderId(orderId);
        long id=orderEntity.getId();
        if(orderEntity==null)throw  new Exception();
     orderEntity =OrderTransformer.orderEntityFromOrderDto(order);

     orderEntity.setId(id);

    orderEntity = orderRepository.save(orderEntity);

    order =OrderTransformer.orderDtoFromOrderEntity(orderEntity);

        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception
    {
        OrderEntity orderEntity= orderRepository.findByOrderId(orderId);
        if(orderEntity==null)throw new Exception();

        long id=orderEntity.getId();
        orderRepository.deleteById(id);
        return;
    }

    @Override
    public List<OrderDto> getOrders()
    {
        List<OrderDto>dtoList=new ArrayList<>();
        for(OrderEntity orderEntity: orderRepository.findAll())
        {
            OrderDto orderDto= OrderTransformer.orderDtoFromOrderEntity(orderEntity);
        }
        return dtoList;
    }
}