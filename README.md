# 📝 Spring Boot 커뮤니티 프로젝트

## 1. 프로젝트 개요

Spring Boot를 기반으로 구현한 커뮤니티 프로젝트입니다. 회원가입, 로그인, 게시글 작성 및 수정, 댓글, 좋아요, 프로필 이미지 업로드 등의 기능을 제공합니다.

## 2. 개발 환경

-   Java: Homebrew 17.0.14
-   Spring Boot
-   JPA (Hibernate)
-   MySQL
-   Spring Security + JWT
-   HTML/CSS/JavaScript (Vanilla)
-   파일 업로드: 디스크(디렉토리) 저장 방식
-   Token 기반 인증 (AccessToken + RefreshToken)
-   추후 Redis 도입 고려 (RefreshToken 관리용)

## 3. 사용 라이브러리 및 의존성

```gradle
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-validation'
implementation 'org.projectlombok:lombok'
```

-   `application.yml`에서 MySQL 연결 설정 추가

---

## 📁 프로젝트 구조

-   `controller`: API 요청 처리
-   `service`: 비즈니스 로직
-   `repository`: 데이터 접근
-   `dto`: 요청/응답용 객체
-   `entity`: DB 테이블 매핑
-   `config`: 보안 및 정적 리소스 설정
-   `common`, `global`: 공통 응답, 예외 처리 등
-   `filter`: JWT Filter 등 필터
-   `util`: JWT 생성, 검증 등 도구

---

## 📌 공통 응답 형식 (ApiResponse)

모든 응답은 아래 형식을 따릅니다:

```json
{
	"statusCode": 200,
	"message": "SUCCESS",
	"data": {}
}
```

---

## ✅ TODO

-   좋아요 요청 시, 불필요한 쿼리 호출 여부 확인 및 최적화 필요  
    (예: `likeList` 전체를 조회하지 않고 별도의 `flag` 사용 등)
-   다른 API에도 불필요한 쿼리 호출이 없는지 점검 필요
-   인증을 위해 `getMe` 호출 시, 실제 인증과 데이터 조회를 분리할지에 대한 고민  
    (예: `getMe`는 한 번만 호출하고, 인증 여부만 확인하는 별도 API 고려)
-   댓글 등록 후 리렌더링 방식 개선  
    (현재는 새로고침 후 반영. 댓글만 다시 fetch하여 부분 렌더링하는 방식 고려)
-   전체 API 흐름 점검 및 불필요한 연산/쿼리 최소화
-   `@Transactional`을 필요한 메서드에만 설정하여 최적화

---

## 🌈 회고
이번 프로젝트에서는 처음으로 백엔드를 Spring Boot로 직접 설계하고 구현해보는 경험을 했습니다. API 설계부터 DB 연동, 간단한 예외 처리까지 처음부터 끝까지 백엔드 흐름을 손으로 짚어보면서, 프론트엔드 개발자로서 한 단계 성장할 수 있었던 시간이었다고 느낍니다.
무엇보다도, 앞으로 협업할 때 백엔드 개발자의 입장에서 더 잘 이해하고 소통할 수 있겠다는 자신감이 생겼다는 점이 가장 큰 수확이었습니다.

물론 아직 구현하지 못한 부분도 있습니다. 예를 들어 AWS S3를 활용한 이미지 저장 기능이나 토큰 기반 인증 로그인 등은 다음에 도전해보기로 했습니다. 이번 주까지는 백엔드 개발을 마무리하고, 다음 주부터는 프론트엔드 고도화 작업에 집중할 예정입니다.

개발을 진행하면서 인상 깊었던 건, GPT가 백엔드의 CRUD 같은 기본적인 기능 코드를 굉장히 잘 짜준다는 점이었습니다. 덕분에 반복적인 작업에 드는 시간을 줄이고, 더 중요한 로직이나 구조 고민에 집중할 수 있었습니다. 이런 경험을 통해, 앞으로 프론트엔드 개발을 할 때에도 GPT와 같은 도구를 적극 활용하면서 반복적인 코드 작성을 줄이고, 보다 고도화된 기능이나 최적화에 집중할 수 있는 개발자가 경쟁력을 갖추게 될 것이라는 생각이 들었습니다.
