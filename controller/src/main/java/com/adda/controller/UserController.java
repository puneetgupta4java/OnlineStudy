package com.adda.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adda.entity.User;
import com.adda.security.token.JwtTokenProvider;
import com.adda.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/adda-admin")
@Api(value = "/adda-admin")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@RequestMapping(value = "/createUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ApiOperation(value = "/createUser", notes = "This API is used to create new user", response = ResponseEntity.class, protocols = "http,https")
	@ApiResponses({ @ApiResponse(code = 200, message = "User created sucessfully"),
			@ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<ServiceResponse> createUser(@RequestBody User newUser) {

		LOGGER.info("Request for New user for registration");
		ServiceResponse response = null;
		List<Object> resultSet = null;
		newUser.setRoles(Arrays.asList("ROLE_USER"));
		User user = userService.createUser(newUser);
		if (user != null) {
			resultSet = new ArrayList<>();
			response = new ServiceResponse();
			resultSet.add(user);
			response.setResultSet(resultSet);
			LOGGER.info("Registration of user done successfully");
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.CREATED);
		} else
			return new ResponseEntity<ServiceResponse>(
					ServiceResponse.createFailureResponse("User email already exists!"), HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	@ApiOperation(value = "/login", notes = "This API is used to login user", response = ResponseEntity.class, protocols = "http,https")
	@ApiResponses({ @ApiResponse(code = 200, message = "User loggedin sucessfully"),
			@ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
		Map<String, String> response = new HashMap<>();
		String email = user.getEmail();
		String password = user.getPassword();
		User oldUser = userService.getUser(email, password);
		String token = tokenProvider.createToken(email, oldUser.getRoles());
		response.put("Name", oldUser.getName());
		response.put("token", token);
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/removeUser", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@ApiOperation(value = "/removeUser", notes = "This API is used to delete user", response = ResponseEntity.class, protocols = "http,https")
	@ApiResponses({ @ApiResponse(code = 200, message = "User deleted sucessfully") })
	public ResponseEntity<ServiceResponse> deleteUser(@RequestBody User user) {
		userService.removeUser(user);
		return new ResponseEntity<ServiceResponse>(ServiceResponse.createSuccessResponse("Removed user Successfully!"),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ApiOperation(value = "/updateUser", notes = "This API is used to update an existing user", response = ResponseEntity.class, protocols = "http,https")
	@ApiResponses({ @ApiResponse(code = 200, message = "User data updated sucessfully"),
			@ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<ServiceResponse> updateUser(@RequestBody User user) {
		User oldUser = userService.updateUser(user);
		ServiceResponse response = null;
		List<Object> resultSet = null;
		if (oldUser != null) {
			resultSet = new ArrayList<>();
			response = new ServiceResponse();
			resultSet.add(oldUser);
			response.setResultSet(resultSet);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		} else
			return new ResponseEntity<ServiceResponse>(
					ServiceResponse.createFailureResponse("Couldn't find user with the given information"),
					HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "/getAllUsers", notes = "This API is used to get the data of existing users", response = ResponseEntity.class, protocols = "http,https")
	@ApiResponses({ @ApiResponse(code = 200, message = "User data sent sucessfully"),
			@ApiResponse(code = 202, message = "Accepted") })
	 @RequestMapping(value="/getAllUsers", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<ServiceResponse> getAllUsers(@RequestParam("property") Optional<String> property,
    												   @RequestParam("accState") Optional<String> accState,
    		                                           @RequestParam(value="page", defaultValue="0") int page,
    		                                           @RequestParam(value="size", defaultValue="3") int size,
    		                                           @RequestParam(value="orderBy", defaultValue="ASC") String orderBy,
    		                                           @RequestParam(value="sortBy", defaultValue="name") String sortBy) throws Exception {
    	Page<User> users = userService.findAll(property.orElse("_"), accState.orElse("_"), PageRequest.of(page, size,
    			           Sort.by("asc".equalsIgnoreCase(orderBy)?Sort.Direction.ASC:Sort.Direction.DESC, sortBy)));
    	ServiceResponse response=null;
    	List<Object> resultSet=null;
    	if(users.isEmpty())
    		return new ResponseEntity<ServiceResponse>(
					ServiceResponse.createFailureResponse("No records"),
					HttpStatus.ACCEPTED);
    	else {
    		response=new ServiceResponse();
    		resultSet=new ArrayList<>();
    		resultSet.add(users.getNumber());
    		resultSet.add(users.getNumberOfElements());
    		resultSet.add(users.getContent());
    		resultSet.add(users.getTotalPages());
    		resultSet.add(users.getSize());
    		response.setResultSet(resultSet);
    		response.setMessage("Fetch records successfully");
    		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
    	}
    		
    }

}
