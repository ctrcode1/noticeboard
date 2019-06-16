공지 사항 게시판
======================

개발 환경
-------------

* Spring Tool Suite 4
* Java 8
* Gradle(Buildship 3.x)
* H2 DB(in memory)
* Spring Boot 2.1.5 with Web, Security, thymeleaf and data JPA
* bootstrap 3.3.5


프로젝트 구성 방법
-------------

* Github에서 프로젝트 다운로드
* zip 다운로드인 경우 압축을 해제하고 프로젝트 디렉토리 명을 다음으로 변경
  * noticeboard-master -> rsupport_pretest
* STS 실행 후 project import
  * File -> Import -> Existing Gradle Project
  
  ![image](https://user-images.githubusercontent.com/4945833/59558956-68a93a00-9038-11e9-8c78-efd2a530d523.png)
  
  * Project Root Directory에 압축을 해제한 rsupport_pretest 디렉토리를 선택 후 Finish 버튼 선택
  
  ![image](https://user-images.githubusercontent.com/4945833/59558971-d6556600-9038-11e9-911f-f4f445f6cbdc.png)
  
  * Project Import가 끝나면 Project Explorer에 프로젝트 생성
  
  ![image](https://user-images.githubusercontent.com/4945833/59558988-6b585f00-9039-11e9-8a0e-8b89cc3cc38b.png)

  

Source Package 구성
-------------

* Java source
  * com.rsupport.pretest
    * project base package
    * Spring Boot application main class 포함
  * com.rsupport.pretest.config
    * application config class
    * Security 처리를 위한 in-memory User 설정
    * Login 후 default page 처리를 위한 success handler
  * com.rsupport.pretest.controller
    * Request 수신을 위한 Controller class
  * com.rsupport.pretest.model.dto
    * view layer에 연동할 data object
  * com.rsupport.pretest.model.entity
    * Database entity 정의
  * com.rsupport.pretest.repository
    * Database query를 위한 JPA Repository
  * com.rsupport.pretest.repository.specs
    * 조건 검색을 위한 Specification class 정의
  * com.rsupport.pretest.service.impl
    * Business service class
    * 금번 Pretest Project는 업무 요건이 간단하고 동일 특성을 가지는 Business가 존재하지 않아 service interface package layer는 제외함

* html script : /rsupport_pretest/src/main/resources/templates
  * login.html : 로그인
  * notice_list.html : 공지 사항 목록
  * notice_new.html : 새 공지 사항 등록
  * notice_detail.html : 기존 공지 사항 상세
  
* 기타
  * /rsupport_pretest/src/main/resources/schema.sql
    * 공지 사항 저장용 Table 정의 - application load 시 H2 DB에의 테이블 생성 Script

STS에서의 실행
-------------
1. Application Main을 통한 실행
    - supportPretestApplication.java 파일 open
    - 편집 창에서 mouse R-Click
    - Run As / Debug As -> Spring Boot App 선택
  
    ![image](https://user-images.githubusercontent.com/4945833/59559103-862bd300-903b-11e9-8e82-5ac60324ede7.png)
  
2. STS Tool Button을 이용한 실행
    - STS 화면 상단 Tool Bar의 Debug 버튼 또는 Run Button에서 'rsupport_pretest'를 선택해서 실행
    
    ![image](https://user-images.githubusercontent.com/4945833/59559179-ba53c380-903c-11e9-93e5-ae61549ed924.png)


Stand-Alone 실행
-------------

기능 테스트
-------------
1. 초기 접속 : http://localhost:8080/
    - 사용 가능 User : Spring Security in-memory 
      - ID : user1(Password : password1)
      - ID : user2(Password : password2)
    - 로그인 후 공지사항 목록 첫 페이지로 이동
      
    ![image](https://user-images.githubusercontent.com/4945833/59559223-8d53e080-903d-11e9-9d7f-9a986cb8068b.png)

2. 목록 화면
    - 로그인 후 기본 화면은 공지사항 목록의 첫 페이지로 이동
    - 목록의 표출 순서는 최종수정일 기준 Descending
    - 하단의 페이지 번호 링크를 통해서 목록의 나머지 페이지로 이동 가능
    - 입력창에 사용자 ID를 입력하고 '사용자별 조회'를 선택하면 최초 작성자가 입력창 조건인 데이터를 조회하여 표출
    - '새 공지 만들기' 버튼을 이용하여 공지 사항 등록 화면으로 이동
    - 테이블 목록을 클릭하여 상세 정보로 이동
    - Sign Out 버튼을 이용하여 기능 종료 및 로그인 화면으로 이동
    
    ![image](https://user-images.githubusercontent.com/4945833/59559437-49afa580-9042-11e9-9360-7d5a9b8595ac.png)
    
    
3. 공지 사항 등록
    - 취소 버튼을 선택하면 이전 목록 화면으로 이동
    - 화면에서 제목과 공지 사항 내용을 입력 후 등록 버튼을 선택하면 공지 사항이 저장되고 목록 화면으로 이동
    - 등록 시 작성자, 최종수정자는 로그인 한 사용자 ID로 저장
    
    ![image](https://user-images.githubusercontent.com/4945833/59559450-88ddf680-9042-11e9-8d83-a2cb34197eae.png)

    ![image](https://user-images.githubusercontent.com/4945833/59559456-9abf9980-9042-11e9-9126-76db433be8b4.png)


4. 공지 사항 상세 화면
    - 목록에서 선택한 공지 사항의 상세 내용을 표출
    - 취소 버튼을 선택하면 이전 목록 화면으로 이동
    - 제목과 공지 내용을 수정한 후 수정 버튼을 선택하면 수정된 내용이 공지 사항에 저장되고 목록 화면으로 이동
    - 수정 시 최종수정자는 로그인 한 사용자 ID로 저장
    
    ![image](https://user-images.githubusercontent.com/4945833/59559540-b8d9c980-9043-11e9-90a6-bdbe64baf000.png)

    ![image](https://user-images.githubusercontent.com/4945833/59559550-cee78a00-9043-11e9-8969-b7553c0ac21a.png)

    - 상세 화면에서 삭제 버튼을 선택하면 공지 사항을 삭제하고 목록 화면으로 이동
    
    ![image](https://user-images.githubusercontent.com/4945833/59559661-21c14180-9044-11e9-8400-357489ce5614.png)

    ![image](https://user-images.githubusercontent.com/4945833/59559700-34d41180-9044-11e9-80ab-242231058fe2.png)
    
