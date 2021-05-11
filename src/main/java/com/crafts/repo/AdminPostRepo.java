package com.crafts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crafts.entity.AdminPost;

@Repository
public interface AdminPostRepo extends JpaRepository<AdminPost,String> {

}