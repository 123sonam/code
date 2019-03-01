package com.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.User;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.utill.APiStatus;
import com.test.utill.ResponseMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	

	
	@Override
	public APiStatus<User> addUser(User user) {
		// TODO Auto-generated method stub
		
		if(userRepo.findByEmail(user.getEmail())==null)
		{
		User u = userRepo.save(user);
		
		
		return (u!=null) ?
			 new APiStatus<>(ResponseMessage.SUCCESS,ResponseMessage.REGISTER_SUCCESS,user):
			 new APiStatus<>(ResponseMessage.FAILED,ResponseMessage.REGISTER_FAILED,null);
		}
		else {
			return new APiStatus<>(ResponseMessage.FAILED,ResponseMessage.EMAIL_EXIST,null);
		}
		
	
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		
		return userRepo.findById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public APiStatus<User> login(User user) {
		// TODO Auto-generated method stub
		User user2=userRepo.findByEmail(user.getEmail());
		if(user2!=null) {
			User user3=userRepo.findByPassword(user.getPassword());
			return (user3!=null) ?
					 new APiStatus<>(ResponseMessage.SUCCESS,ResponseMessage.LOGIN_SUCCESS,user):
					 new APiStatus<>(ResponseMessage.FAILED,ResponseMessage.USER_EXIST,null);
		}else {
			return new APiStatus<>(ResponseMessage.FAILED,ResponseMessage.EMAIL_EXIST,null);
		}
		
			}
	

	@Override
	public APiStatus<User> deleteUser(int id) {
		// TODO Auto-generated method stub
		User u= userRepo.findById(id);
		if(u!=null) {
			u.setDeleted(true);
			userRepo.save(u);
			return new APiStatus<>(ResponseMessage.SUCCESS,ResponseMessage.DELETE_SUCCESS,u);
		}else {
		return new APiStatus<>(ResponseMessage.FAILED,ResponseMessage.USER_EXIST,null);
	}
	}
	@Override
	public APiStatus<User> updateUser(User user) {
		// TODO Auto-generated method stub
		
			User user1 = userRepo.save(user);
			return new APiStatus<>(ResponseMessage.SUCCESS,ResponseMessage.UPDATE_SUCCESS,user);
}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();//hello
	}

}
