package com.crafts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crafts.entity.ContactInfo;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo, String>{

	
}
