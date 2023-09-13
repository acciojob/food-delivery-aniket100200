package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Transformers.OrderTransformer;
import com.driver.io.repository.OrderRepository;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order)
	{
		OrderDto orderDto= OrderTransformer.orderDtoFromOrderDetailRequestModel(order);

		orderDto=orderService.createOrder(orderDto);

		OrderDetailsResponse orderDetailsResponse=OrderTransformer.orderDetailResponceFromOrderDto(orderDto);

		return orderDetailsResponse;
	}

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception
	{
		try
		{
			OrderDto orderDto= orderService.getOrderById(id);
			OrderDetailsResponse orderDetailsResponse=OrderTransformer.orderDetailResponceFromOrderDto(orderDto);

			return orderDetailsResponse;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto dto=OrderTransformer.orderDtoFromOrderDetailRequestModel(order);
		try
		{
			dto=orderService.updateOrderDetails(id,dto);

			OrderDetailsResponse response=OrderTransformer.orderDetailResponceFromOrderDto(dto);
			return response;
		}
		catch(Exception e)
		{
			return null;
		}

	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception
	{
		try
		{
			orderService.deleteOrder(id);

			OperationStatusModel operationStatusModel=new OperationStatusModel();
			operationStatusModel.setOperationResult("Successful");
			operationStatusModel.setOperationName("deleteOrder");
			return operationStatusModel;
		}
		catch(Exception e)
		{
			return null;
		}

	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders()
	{
		List<OrderDto>dtoList=orderService.getOrders();

		List<OrderDetailsResponse>orderDetailsResponseList=new ArrayList<>();

		for(OrderDto dto:dtoList){
			OrderDetailsResponse orderDetailsResponse=OrderTransformer.orderDetailResponceFromOrderDto(dto);

			orderDetailsResponseList.add(orderDetailsResponse);
		}
		return orderDetailsResponseList;
	}
}
