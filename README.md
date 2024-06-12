## 자바프로그래밍 3팀 - 공금 관리 프로그램
- 자바프로그래밍 3팀 프로젝트 최종 결과물입니다.
- The semester of course: 2024-Spring / COMP0217-001
- Professor: Suh, Young-Kyoon

### 팀원 구성
- 노성민: 기획, UI/UX, 디자인, PPT
- 서형철: 기획, 기능 설계 및 구현
- 이다은: 기획, UI/UX, 발표 및 Demo 영상제작

### Swing, ServiceLogic 리포지토리에 Readme 를 별도로 작성하였습니다.
- 세부 사항은 각 Readme 를 참고해주시면 감사드리겠습니다.
- (README_Swing.md, README_ServiceLogic.md)

-----

### 개발 환경
- OpenJDK 1.8
- IntelliJ + Eclipse
- 메일 발송 서버 (MTA): Postfix (별도의 서버를 구축)

### 프로그램 실행 전 작업
- 서버 접속 정보 수정 (메일서버 + SFTP 파일서버)
	- 서버 IP 및 접속 포트, 유저 ID 및 비밀번호를 기입하여야 합니다.
	- 보안 유출 우려로, GitHUB 에는 더미 데이터를 올려두었습니다. PPT 에 기재된 대로 수정하여야 정상 작동하니 양해 부탁드립니다.
	- 모든 설정값은 문자열 형태로 입력되어야 합니다.
- 몇몇 외부 라이브러리. (아래에 목록이 있습니다.)

### 사용한 외부 라이브러리
- jsch-0.1.55.jar // https://sourceforge.net/projects/jsch/files/jsch.jar/0.1.55/jsch-0.1.55.jar/download
- json-simple-1.1.1.jar // https://code.google.com/archive/p/json-simple/downloads
- mail-1.4.7.jar // https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
- hamcrest-core-1.1.jar (for test in IntelliJ. Not used in Program Sources Code.)
- junit-4.10.jar (for test in IntellJ. Not used in Program Sources Code.)

### 데이터 파일은 어디에 저장되는가?
- 프로그램은 크게 멤버 데이터와 그룹 데이터를 분리하여 저장합니다.
- ./src 디렉토리 안에, allMemberList.dat 및 allGroupList.dat 파일로 저장됩니다.
- 프로그램이 실행되면, 서버에 저장된 파일을 다운로드받습니다. 서버에 파일이 없다면 로컬에 저장된 것을 사용합니다.
- 로컬에도 파일이 없다면, 빈 파일을 하나 생성하여 사용하게 됩니다.
- 프로그램을 닫으면, 변경된 멤버/그룹 정보를 저장하고 서버에 업로드합니다.

-----

### 소스코드 구조
- MVC 패턴의 적용을 시도해보았습니다.
- Swing <-> (data.java) <-> Controller <-> Service <-> Entity
- data.java 파일은 Controller 의 여러 메소드를 조합하여 만든 새로운 클래스입니다.
- Swing 에서 메소드를 호출할 때, Controller 의 여러 메소드를 조합하지 않고, 하나의 메소드만 불러도 처리할 수 있도록 합니다.
- 프로그램에 로그인한 각 사용자를 기억하는, 즉 "세션" 유지의 역할을 수행할 수 있습니다. (data.java 에 세션 정보를 저장)

-----

### 클래스 구현
- README_Swing 및 README_ServiceLogic.md 를 참고해 주시면 감사드리겠습니다.

---
### 라인 수
- 약 2,100 + 3,500 = 5,600 Line. (주석 포함)
- [ServiceLogic]
- ![image](https://github.com/Team3-COMP0217001/serviceLogic/assets/77498822/9957c77b-6121-4408-a0ac-b280eefdaaa4)
- [Swing]
- ![image](https://github.com/Team3-COMP0217001/swing/assets/77498822/a9e5db55-92dc-4668-a090-ffbfdafdfed4)
---

### Last Changed: 2024. 06. 10 (Wed.)
- Add Information: 2024. 06. 12 (Wed.)
- Add Information: 2024. 06. 10 (Mon.)
- First Created: 2024. 05. 13. (Mon.)
