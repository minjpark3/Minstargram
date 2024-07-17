# 인스타그램을 이길 수 있는 민스타그램이 될때까지

## 🙌 Minstargram
인스타그램 클론코딩으로 스토리 페이지를 스크롤 페이징 형식으로 구현하여 댓글, 좋아요, 구독하기 기능을 통해 사용자 간의 소통을 원활하게 합니다. AWS S3를 이용하여 대규모 사용자 접속과 사진 업로드를 효율적으로 관리하며, 인기 페이지 기능을 통해 팔로워된 사람들의 페이지를 쉽게 확인 할 수 있습니다.

## 🛠 기능 엿보기   

### Tech Stack👉
![기술스택1](https://github.com/user-attachments/assets/1e17abc3-c62d-4146-b7fd-6cf447f407e7)

### ERD👉
![스크린샷 2024-07-17 153242](https://github.com/user-attachments/assets/6b1a6284-971a-4a99-af63-686ea8ddb3bd)

## ❓ 인메모리 DB로 테스트 하는 yml, maven  

### 의존성
Sring Boot DevTools <br>
Lombok<br>
Spring Data JPA<br>
MariaDB Driver<br>
Spring Security<br>
Spring Web<br>
oauth2-client<br>

     server:
       port: 8080
       servlet:
        context-path: /
        encoding:
          charset: UTF-8
          enabled: true

    spring:
      mvc:
        view:
          prefix: /WEB-INF/views/
          suffix: .jsp

      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/{디비 이름}    
        username: {username}
        password: {password}


      jpa:
        open-in-view: true
        hibernate:
          ddl-auto: create #create ,update ,none
          naming:
            physical-strategy:org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
        show-sql: true

      servlet:
        multipart:
          enabled: true
          max-file-size: 2MB

      security:
        user:
          name: {name}
          password: {password}

    cloud:
      aws:
        s3:
          bucket: {버킷이름}
        stack.auto: false
        region.static: ap-northeast-2
        credentials:
          accessKey: {accessKey}
          secretKey: {secretKey}

    file:
      path: C:/workspace/upload/

## Author
All developments : 🙋‍♀️ Minjeong Park
