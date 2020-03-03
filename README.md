# books

### 프로젝트 설명
프로젝트는 총 6개 모듈로 구성 됐습니다. 모든 업무로직은 books-core-impl에서 처리하고 있고 Spring의 Http Invoker 방식으로 외부에 각 Service API를 제공합니다. 아래는 각 모듈에 대한 설명입니다.
 
#### books-authorization
사용자 로그인 성공 시 Access Token 발급하고, Rest API호출 시 헤더에 있는 Access Token를 체크합니다. 동릭적으로 실행이 됩니다.  
 
#### books-core
각종 업무를 처리하는 Service Interface가 들어 있습니다.

#### books-core-impl
books-core를 구현했고 업무를 처리하는 모듈입니다. Spring의 Http Invoker 방식으로 외부에 각 Service API를 제공합니다. 동릭적으로 실행이 됩니다.  
 
#### books-endpoint
Rest API를 제공하는 모듈입니다. 사용자들이 Rest API를 통해 책 검색, 인기 키워드 조회 등 기능을 사용할 수 있습니다. 동릭적으로 실행이 됩니다.  

#### books-kakao
Kakao 책검색 API와 연동하는 Library 입니다. books-core-impl에서 dependency해서 사용합니다.

#### books-naver
Naver 책검색 API와 연동하는 Library 입니다. books-core-impl에서 dependency해서 사용합니다.

### 프로젝트 실행 방법
#### 컴파일
우선 프로젝트 root에서 maven 명령어 `mvn clean install` 실행합니다. 실행이 실패 될 경우 books-kakao, books-naver, books-core, books-core-impl, books-authorization, books-endpoint 순서대로 `mvn clean install` 명령어를 실행하세요.

#### 실행
컴파일하고 나서 IntelliJ IDEA에서 아래 순서대로 각 모듈을 실행합니다.
1. 우선 books-core-impl 모듈을 실행합니다.
2. 그 다음 books-authorization 모듈을 실행합니다.
3. 마지막 books-endpoint 모듈을 실행합니다.

#### API 테스트 방법  
프로젝트 목록에 books-client.http 파일 참조