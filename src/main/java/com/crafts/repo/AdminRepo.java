package com.crafts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crafts.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin,String> {

}

