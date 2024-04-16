package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {

        val sidney = userRepository.save(User("SSperandio", "Sidney", "Sperandio"))
        articleRepository.save(Article(
            title = "My First Article",
            headline = "This is my first article",
            content = "This is content of my first article.",
            author = sidney
        ))
        articleRepository.save(Article(
            title = "My Second Article",
            headline = "This is my second article",
            content = "This is content of my second article.",
            author = sidney
        ))
    }
}