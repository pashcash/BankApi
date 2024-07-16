# BankApi

## Запуск приложения
Необходимо установить:
- Java 17+
- Maven 3.5+

Клонировать или скачать репозиторий и перейти к нему:
- На Windows: `cd ..\BankApi`
- На Linux: `cd BankApi`
  
Запустить проект: 
- На Windows: `./mvnw spring-boot:run`
- На Linux: `mvn spring-boot:run`

## Редактирование БД
Для редактирования базы данных:
- Запустить проект
- Зайти в браузер
- Перейти по адресу `http://localhost:8080/h2`

## Запросы к API
GET запросы (entity может быть **banks**, **clients**, **deposits**):
- Получить все сущности - `http://localhost:8080/entity`
- Получить все сущности, отсортированные по столбцу **id** - `http://localhost:8080/entity?sort-by=id`

POST запросы (entity может быть **banks**, **clients**, **deposits**):
- Добавить сущность - `http://localhost:8080/entity`
- В теле запроса передать данные о сущности без **id**

PUT запросы (entity может быть **banks**, **clients**, **deposits**):
- Обновить данные сущности **id=1** - `http://localhost:8080\entity/1`
- В теле запроса передать данные о сущности без **id**

DELETE запросы (entity может быть **banks**, **clients**, **deposits**):
- Удалить запись по **id=1** - `http://localhost:8080/entity/1`
