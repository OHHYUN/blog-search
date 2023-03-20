# blog-search
hexagonal architechture 

# 기능
### Kakao Blog 검색
### 인기 검색어 목록
### Pagination 응답 값 적용
# 앞으로 추가해야 할 사항

## 검색 키워드 저장 
Spring Event를 검색 이벤트가 발생할 경우 이벤트 발행

동시성 보장(db lock 사용) 

## Naver Blog 검색
Kakao Blog 검색 실패 시 처리 
Infra영역에서 할지? Service에서 할지?

## Exception 처리
Exception 이나 잘못된 요청값이 들어올 경우 처리
RestControllerAdvice 사용하여 처리 

## RestAPI 문서 작성 README에 추가

## BUILD TEST

# 이슈


# 궁금증
각 계층 이동 시 객체 변환을 하기 때문에 이것 때문에 성능이나 문제가 생길까?

repository 인터페이스를 분리를 해야할까..?

제약조건이 JPA 이기 때문에 R2dbc 사용할 수 없음 추후에 확장 가능성을 위해 webflux를 게속 사용하는것이 맞을까?