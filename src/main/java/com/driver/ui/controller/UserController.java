package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.repository.UserRepository;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
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
@RequestMapping("/users")
public class UserController {
@Autowired
private UserService userService;
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception
	{
		UserDto userDto=userService.getUserByUserId(id);
		UserResponse userResponse=new UserResponse();
		userResponse.setUserId(userDto.getUserId());
		userResponse.setEmail(userDto.getEmail());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());

		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		UserDto userDto=new UserDto();
		userDto.setEmail(userDetails.getEmail());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userDto.setUserId("test");

		try
		{
			userDto = userService.createUser(userDto);
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(userDto.getUserId());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());
		userResponse.setEmail(userDto.getEmail());

		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		UserDto userDto=new UserDto();
		userDto.setEmail(userDetails.getEmail());
		userDto.setLastName(userDetails.getLastName());
		userDto.setFirstName(userDetails.getFirstName());


		userDto=userService.updateUser(id,userDto);

		//Object Createiong for Response..
		UserResponse userResponse=new UserResponse();

		//setting all the values..
		userResponse.setUserId(userDto.getUserId());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());
		userResponse.setEmail(userDetails.getEmail());
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception
	{
		userService.deleteUser(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		operationStatusModel.setOperationName("delete");
		operationStatusModel.setOperationResult("Successful");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers()
	{
		List<UserDto>list=userService.getUsers();
		List<UserResponse>userResponses=new ArrayList<>();
		for(UserDto userDto:list)
		{
			UserResponse userResponse=new UserResponse();

			userResponse.setEmail(userDto.getEmail());
			userResponse.setUserId(userDto.getUserId());
			userResponse.setLastName(userDto.getLastName());
			userResponse.setFirstName(userDto.getFirstName());
			userResponses.add(userResponse);

		}
		return userResponses;
	}
	
}
