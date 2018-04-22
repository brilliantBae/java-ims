# Issue Managing System

깃허브의 이슈관리시스템을 모방해 구현한 프로젝트입니다.

## 기능
* 회원가입, 로그인, 개인정보 수정, 로그아웃 기능, 회원목록 기능
* 로그인 기반 이슈, 마일스톤, 라벨 CRUD
* 파일첨부, 파일다운로드 기능


## 기술 스펙
* Java 1.8
* Spring Framework, Spring Boot
* JPA Hibernate
* HTML/CSS
* JavaScript

## 개발과정에서의 어려움과 해결 과정
### 객체 간 Bidirectional 관계를 맺고 있을때 StackOverFlow 에러 발생

* `Question` 과 `Answer` 가 서로서로 @OneToMany, @ManyToOne 의 관계를 가지는 상황
* `Question`의 필드로 `List<Answer>` 를 가지고 있어 서로가 서로를 찾고 또 서로가 서로를 찾기 때문에 Infinite Recursion problem 이 발생하기 때문에 stack overflow error 발생
*  `List<Answer>`에 @JsonIgnore 를 붙여 한쪽에서 관계를 끊어주어 에러를 해결함.
* 객체들 간 Bidirectional 관계를 맺고 있을때 주의를 요해야 한다는 것을 알게되었고, 상황에서의 해결방법 또한 배울수 있었음.
* 관련 글 링크 : https://medium.com/@jwb8705/java-ims-ajax-1-db6245ad4b04

### 동일 이름 파일 overwrite 문제


- 파일 업로드, 다운로드 구현 과정에서 사용자가 동일한 파일 이름을 입력했을 때 서버 디렉토리에 파일이 저장될때 overwrite 되는 문제점 발생
- 서버 디렉토리에 저장하는 파일의 이름을 uuid 로 매번 생성해 다른 이름을 부여하고 DB에도 `fileName` 을 생성한 uuid 로, 원래 파일명은 `originalFileName` 으로 저장함으로써 
해결함.
- 관련 글 링크 : https://medium.com/@jwb8705/java-ims-file-upload-file-download-e0514a6a3e60

### 올바른 인수테스트 기반 개발(ATDD)방법에 대한 혼동


* Acceptance Test의 테스트코드 안에서 직접 데이터베이스에 접근하여 테스트데이터를 생성함.
* LazyInitializationException 발생
* 에러의 원인을 구글링을 통해 파악함.
* 원인은 Collection의 경우, 포함하고 있는 값이 여러개이기 때문에 lazy loading이 허용되어야 하는데 테스트 코드의 경우 트랜잭션이 없는 상태에서는 default 설정이 lazy loading을 허용하지 않는 것으로 되어있기 때문에 에러가 발생
* 테스트 데이터 역시 브라우저에게 요청을 보내 생성하고, 요청을 통해 생성한 테스트 데이터를 가지고 다른 테스트를 진행함.
* AcceptanceTest는 브라우저의 역할을 대신하는 테스트임을 이번 에러해결을 통해 몸소 느낄수 있었음.
* 관련 글 링크 : https://medium.com/@jwb8705/java-ims-ajax-2-%EC%98%AC%EB%B0%94%EB%A5%B8-atdd-%EB%B0%A9%EB%B2%95-222d3319be21
* https://medium.com/@jwb8705/web-test-a-rest-api-with-java-f29060f1fcc3


## 개발인원 
1명
## 개발기간
2018.01.10 ~ 2018.01.26


---
