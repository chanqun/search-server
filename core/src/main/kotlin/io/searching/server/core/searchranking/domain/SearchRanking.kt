package io.searching.server.core.searchranking.domain

import io.searching.server.core.support.jpa.BaseAggregateRoot
import jakarta.persistence.*
import java.time.OffsetDateTime

/**
 * 인기 검색어 랭킹
 */
@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uq_search_ranking_keyword", columnNames = ["keyword"])
    ]
)
class SearchRanking(

    /** 키워드 */
    @Column(length = 100, nullable = false)
    val keyword: String,

    /** 검색 횟수 */
    var count: Int = 0,

    /** 생성일시 */
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Version
    val version: Long = 0

) : BaseAggregateRoot<SearchRanking>() {
    fun record(count: Int): SearchRanking {
        require(count >= 0)

        this.count = count

        return this
    }
}
