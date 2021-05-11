package com.crafts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crafts.entity.Likes;
import com.crafts.entity.Post;

@Repository
public interface LikeRepo extends JpaRepository<Likes,String> {

}
