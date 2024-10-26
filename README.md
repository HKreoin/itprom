[![Maven workflow](https://github.com/HKreoin/itprom/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/HKreoin/itprom/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/7aa0843e159e2a63aaa9/maintainability)](https://codeclimate.com/github/HKreoin/itprom/maintainability)

## Test application for test case IT PROM

Для запуска нужно набрать команду `make build-run`

Описание запросов OpenAPI [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

## Задача

Разработать web-приложение, содержащее три справочника: профессии, отделы, сотрудники.

Каждый справочник должен содержать функции создания, редактирования, удаления соответствующей сущности (CRUD).

## Описание сущностей

    1. Профессия содержит поля Наименование, Примечание;
    2. Отдел содержит поля Наименование, Примечание, Родительский отдел. Т.е. у отделов может быть иерархия.
    3. Сотрудники содержит поля ФИО, Профессия, Отдел, Примечание
    
## Используемые технологии

    1. Java 21 + Spring Boot 3 (Maven);
    2. JavaScript (TypeScript) + VueJs 3 (дизайн не имеет первостепенного значения) (Vite или Webpack); 
    3. БД H2 embedded;
    
