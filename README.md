# Object Container

Лабораторная работа: Контейнер для объектов

# Описание проекта

Универсальный контейнер для хранения объектов - реализация динамического массива, поддерживающего хранение элементов различных типов. Контейнер автоматически расширяет свою емкость при достижении лимита, обеспечивая гибкость работы с данными.

## Быстрый старт

```bash
# Компиляция
mvn compile

# Запуск
mvn exec:java -Dexec.mainClass="com.container.Main"

# Тесты
javac -d bin test/com/container/ObjectContainerTest.java src/com/container/*.java src/com/container/exceptions/*.java
java -cp bin com.container.ObjectContainerTest

# Документация
mvn javadoc:javadoc

# Очистка проекта
mvn clean

# Генерация JavaDoc документации
mvn javadoc:javadoc

## Структура 
src/
└── com/container/
    ├── exceptions/ContainerException.java
    ├── Main.java
    └── ObjectContainer.java
test/
└── com/container/ObjectContainerTest.java

## Возможности 
- Добавление/удаление элементов
- Поиск и доступ по индексу
- Автоматическое расширение
- Обработка исключений

## Технические требования
- Java 11 или выше
- Maven 3.6 или выше
- JUnit 5 для тестирования

## Автор
Bilovus Olga
