## Java Programming Team 3 - 공금 관리 프로그램
- This is a repository which contains some class relative to service logic.
- The semester of course: 2024-Spring / COMP0217-001
- Professor: Suh, Young-Kyoon

-----

### Development Environment
- OpenJDK 1.8
- IntelliJ
- Mail Transfer Agent(MTA): Postfix (at Remote Server)

### The thing needed to run this program
- Editing Connection Information. (./src/connectionInfo.json)
	- Need to edit host, connection port, Connection User and Password of MailServer, SFTPServer.
	- In the case of MailServer, Need to edit Mail Sender additionally.
	- Be careful that all information written as form of "String".
- Adding External Library. (The list is enumerated below.)

### Used External Librarys
- jsch-0.1.55.jar // https://sourceforge.net/projects/jsch/files/jsch.jar/0.1.55/jsch-0.1.55.jar/download
- json-simple-1.1.1.jar // https://code.google.com/archive/p/json-simple/downloads
- mail-1.4.7.jar // https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
- hamcrest-core-1.1.jar (for test in IntelliJ. Not used in Program Sources Code.)
- junit-4.10.jar (for test in IntellJ. Not used in Program Sources Code.)

### Where to exist data file?
- allGroupList.dat and allMemberList.dat is the file that contain data of this program.
- Located at "./src".
- allGroupList.dat: It contains all group information.
- allMemberList.dat: It contains all member information.
- When starting the program, It trying to download two data file from server. (Data synchronization)
- If either file does not exist on server and local, the missing file is created as an empty automatically.
- When closing the program, changed data is saved to two file(allGroupList.dat, allMemberList.dat), and upload to server automatically.

-----

### Sources Code Structure
- Tried to apply "MVC Patterns"
- Swing <-> (data.java) <-> Controller <-> Service <-> Entity
- data.java is a set of various methods in Controller.
- It is made with a combination of methods of controller, and is connected to Swing.
- It made to the purpose that invoke methods of Controller easily.
  (If It does not exist, some combination of Controller Method will be needed at Swing ActionListener.)
- Thus, Swing invoke some methods in dava.java ONLY.

### Program Iitialization steps
- In the first, Swing will load the information about group and member data from a local file. (Deserialization)
- Loaded information is stored in data class. (at data.java).
- Then, Swing invokes service logic methods in data class using loaded information.

-----

### Class implementation
- The class diagram is below.
### [Full Diagram]
![클래스 다이어그램 drawio](https://github.com/Team3-COMP0217001/serviceLogic/assets/77498822/aba56ade-b7e3-4d31-9b13-83d5cbe4806a)

### [Entity Package Diagram]
![entity](https://github.com/Team3-COMP0217001/serviceLogic/assets/77498822/345cf46b-5b33-4629-b237-86cdfe0aba07)

### [Service Package Diagram]
![service](https://github.com/Team3-COMP0217001/serviceLogic/assets/77498822/1677e33b-0b1e-4b4f-81e9-22decb88a85a)

### [Controller Package Diagram]
![controller](https://github.com/Team3-COMP0217001/serviceLogic/assets/77498822/28d03d1a-f459-43e2-961c-58ba89197943)

### [data.java Diagram]
![data java](https://github.com/Team3-COMP0217001/serviceLogic/assets/77498822/54561c64-9e4c-46fb-98f6-5a915f29103a)

---
### Contact
- Seo, Hyeong-Cheol
- Undergraduate, Dept. of Computer Science & Engineering, Kyungpook Nat'l Univ.
- Please check my [profile](https://github.com/wjdqh6544). Thank you.
  
### Last Changed: 2024. 06. 10 (Wed.)
- Add Information: 2024. 06. 12 (Wed.)
- Add Information: 2024. 06. 10 (Mon.)
- First Created: 2024. 05. 13. (Mon.)
