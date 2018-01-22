package com.example.demo.repository

import com.example.demo.model.Topic
import org.springframework.data.repository.CrudRepository

interface TopicRepository : CrudRepository<Topic, String>
