package com.formacionbdi.springboot.app.covinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionbdi.springboot.app.covinic.models.User;



public interface UserRepository extends JpaRepository<User, Long>{

}
