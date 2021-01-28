
FROM maven:3.6.0-jdk-8-alpine
#coping src of your framework
COPY src /home/SeleniumTestFramework/src
#coping pom file of your framework
COPY pom.xml /home/SeleniumTestFramework
#coping testing.xml file of your framework
#COPY testing.xml /home/SeleniumTestFramework
COPY libs2 /home/SeleniumTestFramework/libs2

#running actual command
RUN mvn -f /home/SeleniumTestFramework/pom.xml clean test -DskipTests=true 