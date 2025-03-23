# 📝 Spring Boot 커뮤니티 프로젝트

## 1. 프로젝트 개요

`SpringBoot`를 기반으로 구현한 커뮤니티 백엔드 프로젝트입니다. 

회원가입, 로그인, 게시글 작성 및 수정, 댓글, 좋아요, 프로필 이미지 업로드 등의 기능을 제공합니다.

## 2. 개발 환경

-   Java: 21.0.6
-   Spring Boot
-   JPA (Hibernate)
-   MySQL

## 3. 사용 라이브러리 및 의존성

```gradle
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'com.mysql:mysql-connector-j'
implementation 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
```

-   `application.yml`에서 MySQL 연결 설정 추가

---

## 📁 프로젝트 구조

-   `controller`: API 요청 처리
-   `service`: 비즈니스 로직
-   `repository`: JPA 인터페이스 (DB 연동)
-   `dto`: 요청/응답에 쓰이는 데이터 전송 객체
-   `domain`: JPA 엔티티 클래스들 (DB 테이블 매핑)
-   `config`: 보안 및 글로벌 설정(CORS)

---

## 📌 공통 응답 형식 (ApiResponse)

모든 응답은 아래 형식을 따릅니다:

```json
{
	"statusCode": 200,
	"message": "상황별 성공 메세지",
	"data": {}
}
```

---

## ✅ TODO

-   좋아요 api
-   `AWS S3`를 활용한 이미지 저장 기능
-   토큰 기반 인증 로그인(jwt token)
-   프로젝트 전체구조 리팩토링 & 고도화
-   테스트 코드 작성

---

## 🌈 회고
이번 프로젝트에서는 처음으로 백엔드를 `SpringBoot`로 직접 설계하고 구현해보는 경험을 했습니다. API 설계부터 DB 연동, 간단한 예외 처리까지 처음부터 끝까지 백엔드 흐름을 손으로 짚어보면서, 프론트엔드 개발자로서 한 단계 성장할 수 있었던 시간이었다고 느꼈습니다.
무엇보다도, 앞으로 협업할 때 백엔드 개발자의 입장에서 더 잘 이해하고 소통할 수 있겠다는 자신감이 생겼다는 점이 가장 큰 수확이었습니다.

물론 아직 구현하지 못한 부분도 있습니다. 하지만, 프론트엔드 고도화 작업에 집중하기 위해 `AWS S3`를 활용한 이미지 저장 기능이나 토큰 기반 인증 로그인, Test코드 작성 등은 다음에 도전해보기로 했습니다.

개발을 진행하면서 인상 깊었던 건, GPT가 백엔드의 CRUD 같은 기본적인 기능 코드를 굉장히 잘 짜준다는 점이었습니다. 덕분에 반복적인 작업에 드는 시간을 줄이고, 더 중요한 로직이나 구조 고민에 집중할 수 있었습니다. 이런 경험을 통해, 앞으로 프론트엔드 개발을 할 때에도 GPT와 같은 도구를 적극 활용하면서 반복적인 코드 작성을 줄이고, 보다 고도화된 기능이나 최적화에 집중할 수 있는 개발자가 경쟁력을 갖추게 될 것이라는 생각이 들었습니다.
