package com.driver.Transformers;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;

public class OrderTransformer
{
    public static OrderDto orderDtoFromOrderDetailRequestModel(OrderDetailsRequestModel requestModel){

        OrderDto dto=new OrderDto();
        dto.setItems(requestModel.getItems());
        dto.setCost(requestModel.getCost());
        dto.setUserId(requestModel.getUserId());
        return dto;
    }


    public static OrderDetailsResponse orderDetailResponceFromOrderDto(OrderDto orderDto)
    {
        OrderDetailsResponse response=new OrderDetailsResponse();

        response.setCost(orderDto.getCost());
        response.setOrderId(orderDto.getOrderId());
        response.setStatus(orderDto.isStatus());
        response.setItems(orderDto.getItems());
        response.setUserId(orderDto.getUserId());
        return response;
    }

    public static OrderDto orderDtoFromOrderEntity(OrderEntity orderEntity)
    {
        OrderDto dto=new OrderDto();

        dto.setStatus(orderEntity.isStatus());
        dto.setCost(orderEntity.getCost());
        dto.setId(orderEntity.getId());
        dto.setItems(orderEntity.getItems());
        dto.setOrderId(orderEntity.getOrderId());
        dto.setUserId(orderEntity.getUserId());

        return dto;
    }

    public static OrderEntity orderEntityFromOrderDto(OrderDto order)
    {
        OrderEntity entity=new OrderEntity();

        entity.setCost(order.getCost());
        entity.setItems(order.getItems());
        entity.setStatus(order.isStatus());
        entity.setUserId(order.getUserId());
        entity.setOrderId(order.getOrderId());
        return entity;
    }
}
