## Дипломный проект по профессии "Тестировщик"
---------------------------------------------
### Документация проекта
>> - [План автоматизации тестирования](https://github.com/IlyaVatlin/diplomproekt3/blob/master/docs/Plan.md).
>> - [Отчет о проведении тестирования](https://github.com/IlyaVatlin/diplomproekt3/blob/master/docs/Report.md).
>> - [Отчет о проведенной автоматизации тестирования](https://github.com/IlyaVatlin/diplomproekt3/blob/master/docs/Summary.md).

### Описание [задания](https://github.com/netology-code/qa-diploma).

### Предусловия работы
>> Для запуска проекта на ПК должны быть установлены:
>> - Intellej IDEA Ultimate
>> - Java 11
>> - Docker
>> - Git
>> 


### Запуск приложения
>> Открыть проект в IntelliJ IDEA Ultimate.
>> 
>> Запустить Docker на ПК.
>> 
>> В терминале IDEA выполнить команду
`docker-compose up`
>>
>> В другой вкладке терминала выполнить команду для запуска приложения на СУБД MySQL
`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar .\aqa-shop.jar`
>>
>> В другой вкладке теринала выполнить команду для запуска тестов
`.\gradlew test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
>>
>> После выполнения тестов для просмотра отчетов в другой вкладке терминала выполнить команды
`.\gradlew allureReport`
>>
>> затем
`.\gradlew allureServe `
>>
>> Завершить работу плагина Ctrl+C.
>> 
>> Для запуска приложения на СУБД PostgreSQL
`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar .\aqa-shop.jar`
>>
>> Тесты запускаются командой в другой вкладке терминала
`.\gradlew test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
