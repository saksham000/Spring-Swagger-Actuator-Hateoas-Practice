package com.rest.webservices.rest_web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.rest_web_services.users.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
