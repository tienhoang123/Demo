package com.example.repository.custorm;

import java.util.List;

import com.example.entity.UserEntity;
import com.example.model.UserModel;
import com.example.paging.Pageable;

public interface UserRepositoryCustorm {

	List<UserEntity> findAll(UserModel model , Pageable pageable);
	Long getTotalItems(UserModel model);
}
