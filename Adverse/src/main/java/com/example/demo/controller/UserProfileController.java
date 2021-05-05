package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.MyPasswordEncoding;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfile;

@RestController
public class UserProfileController {

	private UserProfileMapper mapper;
	
	@Autowired
	private MyPasswordEncoding passwordEncoder;

	public UserProfileController(UserProfileMapper mapper) {
		this.mapper = mapper;
	}

	@RequestMapping("/allUser")
	@ResponseBody
	public List<Map<String, String>> androidTestWithRequestAndResponse(HttpServletRequest request) {
		System.out.println(request.getParameter("title"));

		Map<String, String> result = new HashMap<String, String>();
		List<Map<String, String>> resultlist = new ArrayList<Map<String, String>>();
		List<UserProfile> tempList = mapper.getUserProfileList();
		for (UserProfile user : tempList) {
			result.put("id", user.getId());
			result.put("address", user.getAddress());
			result.put("name", user.getName());
			result.put("phone", user.getPhone());
			resultlist.add(result);
		}

		return resultlist;
	}

	@RequestMapping("/availableId")
	@ResponseBody
	public Boolean getIsAvailableId(HttpServletRequest request) {
		String id = request.getParameter("id");
		int cnt = mapper.getAvailableId(id);
		return true;
	}
	
	@RequestMapping("/user/register")
	@ResponseBody
	public int RegisterUserProfile(HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("password"));
		String password = request.getParameter("password");
		System.out.println(passwordEncoder.encode(password));
		password = passwordEncoder.encode(password);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("password"));
		
		return mapper.registerUserProfile(id, name, password);
	}

	
	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		return mapper.getUserProfile(id);
	}

	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList() {
		return mapper.getUserProfileList();
	}
	
	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address) {
		mapper.insertUserProfile(id, name, phone, address);
	}

	@PostMapping("/user/{id}")
	public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address) {
		mapper.updateUserProfile(id, name, phone, address);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		mapper.deleteUserProfile(id);
	}

}
