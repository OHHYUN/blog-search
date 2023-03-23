
# 카카오뱅크 과제

### Github URL 
https://github.com/OHHYUN/blog-search

### 빌드 경로
Gradle build 시 
interface/build/libs/blog-search.jar 파일로 빌드가 됨
```
java -jar ./interface/build/libs/blog-search.jar
```


# API 명세서

## 블로그 검색 API

### Request

|Name|Type| Description                                        |
|------|---|----------------------------------------------------|
|query|String| 검색어 (필수)                                           |
|start|Integer| 검색 시작 위치 (선택, 기본값: 1)                              |
|sort|String| 정렬 방식 (선택, 지원되는 값: RECENCY, ACCURACY 기본값: RECENCY) |

### Response

|Name|Type| Description                                        |
|------|---|----------------------------------------------------|
|start|Integer| 검색 시작 위치 (선택, 기본값: 1)                              |
|sort|String| 정렬 방식 (선택, 지원되는 값: RECENCY, ACCURACY 기본값: RECENCY) |
|currentPage	|Integer|	현재 페이지 번호|
|itemsPerPage	|Integer|	페이지 당 아이템 수|
|totalItems	|Integer|	전체 검색 결과 수|
|totalPages	|Integer|	전체 페이지 수|
|items	|Array|	검색 결과 목록|
|items.title|	String|	블로그 포스트 제목|
|items.content|	String|	블로그 포스트 내용 요약|
|items.url|	String|	블로그 포스트 URL|
|items.blogName|	String|	블로그 이름|
|items.postThumbnail|	String|	블로그 포스트 썸네일 이미지 URL|
|items.postDateTime|	String|	블로그 포스트 작성 일시 (ISO 8601 형식)|


### sample request
```
curl --location --request GET 'localhost:8080/search/blog?query=돼지&start=1&sort=RECENCY'
```

### sample response

```
{
    "currentPage": 1,
    "itemsPerPage": 20,
    "totalItems": 12989307,
    "totalPages": 649466,
    "items": [
        {
            "title": "~3/12",
            "content": "모인목적 : 더글로리 야물게 즐기기 ( 속내 : <b>돼지</b>파티 ) 하 치떡 진짜 먹고싶어 뒤지는줄 ~ ! 근데 교촌... 딸기도줘서 진짜 감동함  입가심으로 아이스크림까지 제대로 <b>돼지</b>파티 즐겨줌 굿 ~ 아들들아... ",
            "url": "blog.naver.com/llimmy",
            "blogName": "full of joy",
            "postThumbnail": "",
            "postDateTime": "2023-03-22T00:00:00"
        }
    ]
}
```

## 인기 검색어 API

### Request

|Name|Type| Description |
|------|---|------------|
|없음|||

### Response

|Name|Type| Description |
|------|---|------------|
|rank|	Integer|인기 검색어 순위|
|keyword|String|검색어|
|count|Integer|검색 횟수|

### sample request
```
curl --location --request GET 'localhost:8080/search/popular-keywords'
```

### sample response

```
[
    {
        "rank": 1,
        "keyword": "돼지",
        "count": 1
    }
]
```

# blog-search
hexagonal architecture

# 기능
### Kakao Blog 검색
### 실패 시 Naver Blog 검색
### 검색 키워드 저장
### 인기 검색어 목록
### Pagination 응답 값 적용
### Exception 처리
# 이슈
동시성 처리 문제가 제대로 작동하지 않음
로그로 하나씩 쌓고 집계로 불러오는 방법도 있지만 집계 과정에서 속도가 오래걸릴 것 같음

### 소감
동시성 문제는 더 알아보고 제대로 해봐야겠다는 생각이 들었고
설계과정 없이 진행하니 너무 난잡하게 개발했다가 엎고.. 쓸데없는 시행착오가 많았음
다음번엔 좀 더 제대로 진지하게 임해서 테스트를 통과 할 수 있도록 해야겠다..
