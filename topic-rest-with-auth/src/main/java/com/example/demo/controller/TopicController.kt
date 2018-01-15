package com.example.demo.controller

import com.example.demo.model.Topic
import com.example.demo.service.TopicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.BasicErrorController
import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@RestController
class TopicController {

    @Autowired
    private val topicService: TopicService? = null

    private val logger = LoggerFactory.getLogger(this.javaClass)


    val allTopics: List<Topic>
        @GetMapping("/topics")
        get() {
            logger.info("all topics")
            return topicService!!.allTopics
        }

    @GetMapping("/topics/{id}")
    fun getTopic(@PathVariable id: String): Topic {

        logger.info("get topic")
        return topicService!!.getTopic(id)
    }

    @PostMapping(value = "/topics")
    fun addTopic(@RequestBody topic: Topic) {

        logger.info("topic added")
        topicService!!.addTopic(topic)
    }

    @PutMapping(value = "/topics/{id}")
    fun updateTopic(@RequestBody topic: Topic, @PathVariable id: String) {
        logger.info("topic updated")
        topicService!!.updateTopic(id, topic)
    }

    @DeleteMapping(value = "/topics/{id}")
    fun deleteTopic(@RequestBody topic: Topic, @PathVariable id: String) {
        logger.info("topic deleted")
        topicService!!.deleteTopic(id, topic)
    }


}
