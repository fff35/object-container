package com.container;

/**
 * Демонстрационный класс для тестирования контейнера ObjectContainer.
 * Показывает примеры использования основных методов контейнера.
 * 
 * @author Bilovus Olga
 * @version 1.0
 */
public class Main {
    
    /**
     * Основной метод демонстрации работы контейнера.
     * Показывает добавление, получение, поиск и удаление элементов.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        System.out.println("Демонстрация работы контейнера");
        
        // Создаем контейнер для хранения данных о студентах
        ObjectContainer container = new ObjectContainer();

        // Добавляем элементы разных типов
        container.add("Иванов Петр");
        container.add(20);
        container.add("ФИИТ-2023");
        container.add(4.5);

        // Вставляем элемент в определенную позицию
        container.add(2, "Программирование");

        // Получаем элементы по индексу
        String студент = (String) container.get(0);
        Integer возраст = (Integer) container.get(1);
        String предмет = (String) container.get(2);
        
        System.out.println("Студент: " + студент);
        System.out.println("Возраст: " + возраст);
        System.out.println("Предмет: " + предмет);

        // Проверяем наличие элемента
        boolean естьОтличник = container.contains(4.5);
        System.out.println("Есть отличник: " + естьОтличник);

        // Выводим все элементы
        System.out.println("\nВсе данные в контейнере:");
        for (int i = 0; i < container.size(); i++) {
            Object элемент = container.get(i);
            System.out.println("Позиция " + i + ": " + элемент + " (" + элемент.getClass().getSimpleName() + ")");
        }

        // Удаляем элементы
        container.remove(3); // Удаляем оценку
        container.remove("Программирование"); // Удаляем предмет
        
        // Выводим после удаления
        System.out.println("\nПосле удаления некоторых элементов:");
        for (int i = 0; i < container.size(); i++) {
            Object элемент = container.get(i);
            System.out.println("Позиция " + i + ": " + элемент);
        }

        // Демонстрация размера контейнера
        System.out.println("\nТекущий размер контейнера: " + container.size() + " элементов");
    }
}
