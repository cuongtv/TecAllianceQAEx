# Introduction 
**TecAlliance QA Exercise**
**Cuong Tran** @[https://cuongtv.github.io/](https://cuongtv.github.io/)

#I. Installation
1. Install NodeJS @[link](https://nodejs.org/en/)
2. Install JDK (v1.8 or later) @[link](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

> - Add Window System Environment: `JAVA_HOME = "path/to/JDK/folder"`
> - Add System Path: `%JAVA_HOME%\bin`
> - Check java version: `java -version`

3. Setup Maven

> - Download latest Maven version and extract to your local folder @[link](https://maven.apache.org/download.cgi)
> - Add Window System Environement: `MAVEN_HOME = "path/to/MAVEN/folder"`
> -	Add System Path: `%MAVEN_HOME%\bin`
> - Check Maven version: `mvn --version`

4. Browser for Auto Testing:

> - Chrome: latest
> - MS Edge: latest (run this command: `DISM.exe /Online /Add-Capability /CapabilityName:Microsoft.WebDriver~~~~0.0.1.0`)
> - Firefox: latest

#II. Setup and Run Test
1. Pull project from Github repo:

> `git clone https://github.com/cuongtv/TecAllianceQAEx.git`

2. Import as a Maven project to IDE (Eclipse, InteliJ, ..)
3. Build/Update project (For Eclipse IDE: Maven > Update Project...)
4. Right-Click on project name (root folder) > Run As > JUnit Test
5. By default, Chrome driver will be used for run test. You can change the `DriverType.CHROME` value in the **Steps.java file for other browser test run. 
6. The custom test report file will be generated at: `src/test/resources/log/TestLog.log`

#III. Libraries Using In Framework
1. `webdrivermanager` for automatically webdriver
2. `log4j2` for custom log
3. `junit` for test run and assertion
4. `jbehave` for BDD test case design
5. `selenium webdriver` for automating web applications
