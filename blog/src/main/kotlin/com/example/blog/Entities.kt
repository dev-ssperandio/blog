package com.example.blog

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

class Article(
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null
)

class User(
    var login: String,
    var firstName: String,
    var lastName: String,
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null
)