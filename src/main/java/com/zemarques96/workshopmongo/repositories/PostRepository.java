package com.zemarques96.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zemarques96.workshopmongo.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}