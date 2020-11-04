package br.com.gifs.data.model.response

import br.com.gifs.data.model.Gif
import br.com.gifs.data.model.Meta
import br.com.gifs.data.model.Pagination

data class TrendingResponse(
    val data: List<Gif>,
    val pagination: Pagination? = null,
    val meta: Meta? = null
)