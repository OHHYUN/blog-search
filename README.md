# blog-search
hexagonal architecture 

# 기능
### Kakao Blog 검색
실패 시 Naver Blog 검색
### 검색 키워드 저장
동시성 보장(db lock 사용)
### 인기 검색어 목록
### Pagination 응답 값 적용
### Exception 처리
# 앞으로 추가해야 할 사항

## RestAPI 문서 작성 README 추가

## Test 코드 작성

## BUILD TEST

# 이슈
GlobalException에 errorcode 필드를 넣은 것 때문에 serializable 하라고 함..하지만 문제가 계속 발생!!

# 궁금증
각 계층 이동 시 객체 변환을 하기 때문에 이것 때문에 성능이나 문제가 생길까?

repository 인터페이스를 분리를 해야할까..?

제약조건이 JPA 이기 때문에 R2dbc 사용할 수 없음 추후에 확장 가능성을 위해 webflux를 게속 사용하는것이 맞을까?