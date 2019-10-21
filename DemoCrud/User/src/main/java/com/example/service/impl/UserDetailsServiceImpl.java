package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.UserEntity;
import com.example.entity.UserRoleEntity;
import com.example.model.MyUserDetail;
import com.example.model.RoleModel;
import com.example.repository.UserRepository;
import com.example.service.RoleService;
import com.example.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserName(username);

		if (userEntity == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<RoleModel> roleModels = roleService.findRoleByUser(userEntity.getId());
		for (RoleModel item : roleModels) {
			authorities.add(new SimpleGrantedAuthority(item.getName()));
		}
		MyUserDetail userDetails = new MyUserDetail(username, userEntity.getPassword(), true, true, true, true,
				authorities);
		BeanUtils.copyProperties(userEntity, userDetails);
		return userDetails;
	}
}
