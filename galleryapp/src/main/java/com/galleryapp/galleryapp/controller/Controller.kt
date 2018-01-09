package com.galleryapp.galleryapp.controller


import com.galleryapp.galleryapp.data.AnimeRepository
import com.galleryapp.galleryapp.model.Anime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@org.springframework.stereotype.Controller
class Controller {

    @Autowired
    private val animeRepository: AnimeRepository? = null


    @GetMapping("/anime")
    fun getallAnime(modelMap: ModelMap): String {
        val animes = animeRepository!!.allAnime
        modelMap.put("allanime", animes)
        return "index"
    }

    @GetMapping("/anime/{animetitle}")
    fun findAnime(@PathVariable animetitle: String, modelMap: ModelMap): String {
        val anime = animeRepository!!.findByAnimeTitle(animetitle)
        modelMap.put("anime", anime)
        return "anime"
    }
}
