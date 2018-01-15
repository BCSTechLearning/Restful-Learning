package com.example.demo.service

import java.util.ArrayList
import java.util.Arrays

import com.example.demo.model.Topic
import com.example.demo.repository.TopicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TopicService {

    @Autowired
    private val topicRepository: TopicRepository? = null

    internal var topics: MutableList<Topic> = ArrayList(Arrays.asList(
            Topic("spring", "Spring Framework", "Spring Framework Description"),
            Topic("java", "Core Java", "Core Java Description"),
            Topic("javascript", "Javascript", "Javascript Description")
    ))

    val allTopics: List<Topic>
        get() = topics

    fun getTopic(id: String): Topic {
        return topics.stream().filter { t -> t.id == id }.findFirst().get()
    }

    fun addTopic(topic: Topic) {
        topics.add(topic)

    }


    fun updateTopic(id: String, topic: Topic) {

        for (i in topics.indices) {
            val t = topics[i]
            if (t.id == id) {
                topics[i] = topic
                return
            }
        }

    }

    fun deleteTopic(id: String, topic: Topic) {

        topics.removeIf { t -> t.id == id }

    }


}
