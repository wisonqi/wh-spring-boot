package com.sfa.service.impl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sfa.dao.UserDao;
import com.sfa.model.sys.UserEntity;
import com.sfa.service.sys.UserService;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void insert(UserEntity userEntity) {
		userDao.insert(userEntity);
	}

	@Override
	public void del(UserEntity userEntity) {
		userDao.del(userEntity);
	}

	@Override
	public UserEntity getUserEntityByLoginName(String loginName) {
		return userDao.getUserEntityByLoginName(loginName);
	}

	@Override
	public List<UserEntity> usersList(String loginName, int pageSize, int start) {
		return userDao.usersList( loginName,  pageSize,  start);
	}

	@Override
	public Integer usersSize(String loginName, int pageSize, int start) {
		return userDao.usersSize(loginName, pageSize, start);
	}

	@Override
	public void insertUser(UserEntity userEntity) {
		/*String password = null;
		try {
			password = MD5Util.encrypt(userEntity.getPassword());
			userEntity.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		userEntity.setUserPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(userEntity.getUserPassword()));
		userDao.insertUser(userEntity);
	}

	@Override
	public void updateUser(UserEntity userEntity) {
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		userEntity.setUserPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(userEntity.getUserPassword()));
		userDao.updateUser(userEntity);
	}

	@Override
	public void deleteUsers(List<String> groupId) {
		userDao.deleteUsers(groupId);
	}

}
