package com.example.service.impl;
import com.example.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.UserEntity;

import com.example.service.UserService;
import com.example.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto user) {
		// method to consume repository function here

		// because some fields can not be null in the DB we need to set them
		// manually

		UserEntity userEntity = new UserEntity();

		userEntity.setEncryptedPassword("test");
		userEntity.setUserId("testUserId");

		BeanUtils.copyProperties(user, userEntity);

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

}
