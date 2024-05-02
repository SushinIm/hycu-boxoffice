# hycu-boxoffice 개발환경

## 언어 & 버전
- Java Amazon corretto-17

## 프레임워크
- Spring Boot 3.0.6
- JPA
- Jooq

## 빌드 환경
- Gradle
- Linux Docker(x86)

## 데이터베이스
- MySQL 8

## IDE
- IntelliJ

## API 문서화
- Restdoc Swagger

# 개발 규칙

## 코드 컨벤션 IDE 설정
1. Intellij Settings
2. Editor
3. Code Style
4. Scheme 톱니바퀴
5. Import Scheme
6. Intellij IDEA code style XML
7. 프로젝트 내 config\checkstyle\intellij-java-google-style.xml 선택
8. 터미널에서 ./gradlew checkstyleMain 
9. 또는 Gradle -> Tasks -> other -> checkstyleMain 실행

## 커밋 메시지 양식
- 커밋 메시지는 분류:내용(티켓 번호)의 형식으로 작성한다.
- 분류 feat는 신규 기능 구현 커밋에 사용한다.
- 분류 fix는 버그 수정 커밋에 사용한다.
- 분류 docs는 기능에 영향을 주지 않는 문서 수정 커밋에 사용한다.
- 분류 style은 checkstyle 등 기능 변경이 없는 코드 수정 커밋에 사용한다.
- 분류 refactor는 패키지 구조 변경 등 코드 리팩토링 커밋에 사용한다.
- 분류 test는 테스크 코드 작성 커밋에 사용한다.
- 티켓 번호의 경우 선택이지만 작업 이슈의 커밋일 경우 가급적 포함한다.
