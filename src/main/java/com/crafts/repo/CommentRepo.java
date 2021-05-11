package com.crafts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crafts.entity.Comments;

@Repository
public interface CommentRepo extends JpaRepository<Comments,String> {

}

