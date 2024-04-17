package com.example.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val sidney = User("SSperandio", "Sidney", "Sperandio")
        val firstArticle = Article("The First Article", "This is the first article", "and this is your content", sidney)
        val secondArticle = Article("The Second Article", "This is the second article", "and this is your content", sidney)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(firstArticle, secondArticle)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(sidney.login))
            .andExpect(jsonPath("\$.[0].slug").value(firstArticle.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(sidney.login))
            .andExpect(jsonPath("\$.[1].slug").value(secondArticle.slug))
    }

    @Test
    fun `List user`() {
        val sidney = User("SSperandio", "Sidney", "Sperandio")
        val ivanessa = User("IDantas", "Ivanessa", "Dantas")
        every { userRepository.findAll() } returns listOf(sidney, ivanessa)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(sidney.login))
            .andExpect(jsonPath("\$.[1].login").value(ivanessa.login))
    }
}