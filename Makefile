init:
	touch ./.git/info/exclude
    
#	maven-wrapper
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.1
	chmod +x ./mvnw
	echo "/.mvn" >> ./.git/info/exclude
	echo "/mvnw*" >> ./.git/info/exclude

#	lombok
	ln -s ./java ./src/main/lombok
	ln -s ./java ./src/test/lombok
	echo "/src/main/lombok" >> ./.git/info/exclude
	echo "/src/test/lombok" >> ./.git/info/exclude

#	checkstyler
	curl -O https://github.com/checkstyle/checkstyle/raw/master/src/main/resources/google_checks.xml
	echo "/google_checks.xml" >> ./.git/info/exclude


build:
	./mvnw verify
	chmod +x ./target/-0.0.1-SNAPSHOT.jar

run:
	./mvnw spring-boot:run -Dspring.profiles.active=local
#	./target/-0.0.1-SNAPSHOT.jar
#	java -jar --enable-preview ./target/-0.0.1-SNAPSHOT.jar

clear:
	./mvnw clean

test:
	./mvnw test

update:
	./mvnw versions:update-parent versions:update-properties versions:display-plugin-updates
	
delombok:
	./mvnw clean
	mkdir -p ./target/generated-sources/delombok ./target/generated-test-sources/delombok
	./mvnw lombok:delombok lombok:testDelombok

.DEFAULT_GOAL := build-run
build-run: update build run
