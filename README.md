## Дипломный проект профессии «Тестировщик»
В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, 
взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Для покупки тура есть два способа: с помощью карты и в кредит. 
В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate.

Ссылка на дипломный проект https://github.com/netology-code/qa-diploma

## Запуск приложения
Для запуска проекта на ПК должны быть установлены:

Intellej IDEA Ultimate
Java 11
Docker
Git
Склонировать проект на компьютер git clone


Открыть проект в IntelliJ IDEA Ultimate.

Запустить Docker на ПК.

В терминале IDEA выполнить команду docker-compose up

В другой вкладке терминала выполнить команду для запуска приложения на СУБД MySQL java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar

В другой вкладке теринала выполнить команду для запуска тестов .\gradlew test "-Ddb.url=jdbc:mysql://localhost:3306/app"

После выполнения тестов для просмотра отчетов в другой вкладке терминала выполнить команды .\gradlew allureReport

затем .\gradlew allureServe

Завершить работу плагина Ctrl+C.

Для запуска приложения на СУБД PostgreSQL java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar

Тесты запускаются командой в другой вкладке терминала .\gradlew test "-Ddb.url=jdbc:postgresql://localhost:5432/app"

