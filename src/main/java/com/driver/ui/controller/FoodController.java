package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Transformers.FoodTransformer;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails)
	{
		FoodDto foodDto= FoodTransformer.foodDtoFromFoodDetailRequestModel(foodDetails);
		//just create the food.. here we go..

			foodDto = foodService.createFood(foodDto);

		ModelMapper modelMapper=new ModelMapper();

		FoodDto foodDto1=modelMapper.map(foodDetails, FoodDto.class);


		FoodDetailsResponse foodDetailsResponse=FoodTransformer.foodDetailsResponseFromFoodDto(foodDto);
		return foodDetailsResponse;
	}

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception
	{
		FoodDto foodDto= foodService.getFoodById(id);

		FoodDetailsResponse foodDetailsResponse=FoodTransformer.foodDetailsResponseFromFoodDto(foodDto);

		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{

		FoodDto foodDto=FoodTransformer.foodDtoFromFoodDetailRequestModel(foodDetails);

		foodDto= foodService.updateFoodDetails(id,foodDto);


		FoodDetailsResponse foodDetailsResponse=FoodTransformer.foodDetailsResponseFromFoodDto(foodDto);
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception
	{
		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		operationStatusModel.setOperationName("deleteFood");
		operationStatusModel.setOperationResult("Success");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods()
	{
		List<FoodDetailsResponse>foodDetailsResponses=new ArrayList<>();
		List<FoodDto>foodDtos=foodService.getFoods();
		for(FoodDto foodDto:foodDtos){
			FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
			foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
			foodDetailsResponse.setFoodName(foodDto.getFoodName());
			foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
			foodDetailsResponse.setFoodId(foodDto.getFoodId());
			foodDetailsResponses.add(foodDetailsResponse);
		}
		return foodDetailsResponses;
	}
}
