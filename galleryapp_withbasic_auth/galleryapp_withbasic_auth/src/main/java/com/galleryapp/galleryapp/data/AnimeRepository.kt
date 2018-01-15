package com.galleryapp.galleryapp.data

import com.galleryapp.galleryapp.model.Anime
import org.springframework.stereotype.Component

import java.time.LocalDate
import java.util.Arrays

@Component
class AnimeRepository {

    val allAnime: List<Anime>
        get() = animelist


    fun findByAnimeTitle(title: String): Anime? {
        for (anime in animelist) {
            if (anime.animeTitle == title) {
                return anime
            }

        }
        return null
    }

    companion object {

        private val animelist = Arrays.asList(
                Anime("attackontitan", LocalDate.of(2014, 3, 12), "Eren Yeager"),
                Anime("boruto", LocalDate.of(2017, 4, 4), "boruto"),
                Anime("deathnote", LocalDate.of(2007, 3, 12), "Kira"),
                Anime("detectiveconan", LocalDate.of(1994, 6, 11), "Shinichi Kudo"),
                Anime("dragonballsuper", LocalDate.of(2015, 2, 12), "Goku"),
                Anime("onepunchman", LocalDate.of(2016, 4, 5), "Saitama")
        )
    }

}
