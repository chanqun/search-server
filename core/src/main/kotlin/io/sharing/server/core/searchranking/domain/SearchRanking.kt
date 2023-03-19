package io.sharing.server.core.searchranking.domain

import io.sharing.server.core.support.jpa.BaseAggregateRoot
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
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

    /** 검색된 횟수 */
    var count: Int = 0,

    /** 생성일시 */
    val createdAt: OffsetDateTime = OffsetDateTime.now()

) : BaseAggregateRoot<SearchRanking>() {
    fun search() {
        this.count++
    }
}
