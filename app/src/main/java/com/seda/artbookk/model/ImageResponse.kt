package com.seda.artbookk.model

import com.seda.artbookk.model.Hit

data class ImageResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)