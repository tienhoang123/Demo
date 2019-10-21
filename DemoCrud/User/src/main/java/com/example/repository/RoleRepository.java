package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.RoleEntity;
import com.lowagie.text.List;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
