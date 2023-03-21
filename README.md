## API 명세

### 1. 블로그 검색
키워드를 통해 블로그를 검색할 수 있다.
```shell
GET /blogs
```

#### Request
Parameter

| Name | Type | Description | Required |
| --- | --- | --- | --- |
| keyword | String | 검색을 원하는 질의어 | O |
| sort | String | 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy | X |
| page | Integer | 결과 페이지 번호, 1~50 사이의 값, 기본 값 1 | X |

#### Response
pageInfo

| Name | Type | Description |
| --- | --- | --- |
| page | Integer | 현재 페이지 |
| sort | String | 정렬 |
| displayCount | Integer | 한 번에 표시 되는 검색 결과 개수 |
| totalCount | Integer | 총 노출 가능 문서 수 |
| isEnd | Boolean | 현재 페이지 마지막 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음 |

documents

| Name | Type | Description |
| --- | --- | --- |
| title | String | 글 제목 |
| contents | String | 글 요약 |
| url | String | 글 URL |
| blogName | String | 블로그 이름 |
| thumbnail | String | 미리보기 이미지 URL |
| postedAt | Datetime | 글 작성 시간 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss] |

### 2. 인기 검색어 목록
사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공
```shell
GET /search-ranking
```

#### Response
searchRankings

| Name | Type | Description |
| --- | --- | --- |
| ranking | Integer | 순위 |
| keyword | String | 검색 키워드 |
| count | Integer | 검색된 횟수 |


## 오픈 소스 사용 목록
- org.jlleitschuh.gradle.ktlint > 소스 전반적 포멧팅 적용
- io.github.microutils:kotlin-logging > 로깅
- org.springframework.cloud:spring-cloud-starter-openfeign, spring-cloud-starter-loadbalancer > 외부 API 호출을 쉽게할 수 있도록 도와줌
- com.ninja-squad:springmockk > mockk 테스트를 모듈
