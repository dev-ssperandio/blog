package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository
) {
    @Test
    fun `When findByIdOrNull then return Article`() {
        val sidney = User("SSperandio", "Sidney", "Sperandio")
        entityManager.persist(sidney)
        val article = Article("The Article", "Checking the article", "This is the article test.", sidney)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }
    @Test
    fun `When findByLogin then return User`() {
        val sidney = User("SSperandio", "Sidney", "Sperandio")
        entityManager.persist(sidney)
        entityManager.flush()
        val user = userRepository.findByLogin(sidney.login)
        assertThat(user).isEqualTo(sidney)
    }
}