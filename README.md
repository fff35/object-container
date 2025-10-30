# Object Container

Универсальный контейнер для хранения объектов. Реализован на Java без встроенных коллекций.

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

## Автор
Bilovus Olga