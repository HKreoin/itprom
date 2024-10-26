.DEFAULT_GOAL := build-run

build:
	./mvnw clean package

install:
	./mvnw clean install

run:
	java -jar ./target/itprom-0.0.1-SNAPSHOT.jar

build-run:
	build run

lint:
	mvn checkstyle:checkstyle

test:
	mvn test