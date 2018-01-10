package com.galleryapp.galleryapp.controllers

import com.galleryapp.galleryapp.GalleryappApplication
import com.galleryapp.galleryapp.data.AnimeRepository
import com.galleryapp.galleryapp.model.Anime
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@Api(value = "AnimeControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
 class controller : ErrorController {


    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val animeRepository: AnimeRepository? = null

    @GetMapping("/error")
    @ResponseBody()
    override fun getErrorPath(): String {

        return "<script>alert('url not found please type http://localhost:8080/anime')</script>"
    }

    @GetMapping("/anime")
    @ApiOperation("Gets the id")
    @ApiResponses(value = *arrayOf(ApiResponse(code = 200, message = "OK", response = GalleryappApplication::class)))
    fun getallAnime(modelMap: ModelMap): String {


        val animes = animeRepository!!.allAnime
        modelMap.put("allanime", animes)


        logger.info("Logged in Successfully")
        return "index"
    }

    @GetMapping("/anime/{animetitle}")
    fun findAnime(@PathVariable animetitle: String, modelMap: ModelMap): String {
        val anime = animeRepository!!.findByAnimeTitle(animetitle)
        modelMap.put("anime", anime)
        return "anime"
    }


}
