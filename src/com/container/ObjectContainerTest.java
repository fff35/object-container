package com.container;

import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для класса ObjectContainer без использования JUnit
 * 
 * @author Bilovus Olga
 * @version 1.0
 */
public class ObjectContainerTest {
    
    private ObjectContainer container;
    private int testCount = 0;
    private int passedTests = 0;
    private List<String> failedTests = new ArrayList<>();
    
    /**
     * Запуск всех тестов
     */
    public static void main(String[] args) {
        ObjectContainerTest tester = new ObjectContainerTest();
        tester.runAllTests();
    }
    
    /**
     * Запускает все тесты и выводит результаты
     */
    public void runAllTests() {
        System.out.println("=== Запуск тестов ObjectContainer ===\n");
        
        testDefaultConstructor();
        testConstructorWithCapacity();
        testConstructorWithInvalidCapacity();
        testAddElements();
        testAddNullElements();
        testAddAtIndex();
        testAddAtInvalidIndex();
        testGetElement();
        testGetInvalidIndex();
        testRemoveByIndex();
        testRemoveByValue();
        testRemoveNonExistent();
        testContains();
        testFindIndex();
        testClear();
        testToArray();
        testDynamicExpansion();
        testToString();
        testComplexScenario();
        
        System.out.println("\n=== Результаты тестов ===");
        System.out.println("Пройдено: " + passedTests + " из " + testCount + " тестов");
        
        if (passedTests == testCount) {
            System.out.println(" Все тесты прошли успешно!");
        } else {
            System.out.println(" Не все тесты прошли");
            System.out.println("\n=== Проваленные тесты ===");
            for (String failedTest : failedTests) {
                System.out.println(" " + failedTest);
            }
        }
    }
    
    private void beforeEach() {
        container = new ObjectContainer();
    }
    
    private void assertTrue(boolean condition, String message) {
        testCount++;
        if (condition) {
            passedTests++;
            System.out.println("прошли" + message);
        } else {
            failedTests.add(message);
            System.out.println("не прошли " + message);
        }
    }
    
    private void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }
    
    private void assertEquals(Object expected, Object actual, String message) {
        testCount++;
        boolean isEqual = (expected == null) ? actual == null : expected.equals(actual);
        if (isEqual) {
            passedTests++;
            System.out.println("прошли " + message);
        } else {
            failedTests.add(message + " (ожидалось: " + expected + ", получено: " + actual + ")");
            System.out.println("не прошли " + message + " (ожидалось: " + expected + ", получено: " + actual + ")");
        }
    }
    
    private void assertThrows(Runnable code, String expectedMessage, String testName) {
        testCount++;
        try {
            code.run();
            failedTests.add(testName + " (ожидалось исключение)");
            System.out.println("не прошли " + testName + " (ожидалось исключение)");
        } catch (Exception e) {
            if (e.getMessage().contains(expectedMessage)) {
                passedTests++;
                System.out.println("прошли " + testName);
            } else {
                failedTests.add(testName + " (неверное сообщение исключения: " + e.getMessage() + ")");
                System.out.println("не прошли " + testName + " (неверное сообщение исключения: " + e.getMessage() + ")");
            }
        }
    }
    
    public void testDefaultConstructor() {
        beforeEach();
        assertTrue(container != null, "testDefaultConstructor - контейнер создан");
        assertEquals(0, container.size(), "testDefaultConstructor - размер 0");
        assertTrue(container.isEmpty(), "testDefaultConstructor - контейнер пуст");
    }
    
    public void testConstructorWithCapacity() {
        ObjectContainer customContainer = new ObjectContainer(15);
        assertEquals(0, customContainer.size(), "testConstructorWithCapacity - размер 0");
        assertTrue(customContainer.isEmpty(), "testConstructorWithCapacity - контейнер пуст");
    }
    
    public void testConstructorWithInvalidCapacity() {
        assertThrows(() -> new ObjectContainer(0), 
            "положительным числом", "testConstructorWithInvalidCapacity - емкость 0");
        assertThrows(() -> new ObjectContainer(-3), 
            "положительным числом", "testConstructorWithInvalidCapacity - емкость -3");
    }
    
    public void testAddElements() {
        beforeEach();
        container.add("Математика");
        container.add(5);
        container.add(4.8);
        
        assertEquals(3, container.size(), "testAddElements - размер 3");
        assertFalse(container.isEmpty(), "testAddElements - не пуст");
    }
    
    public void testAddNullElements() {
        beforeEach();
        container.add(null);
        container.add("Физика");
        container.add(null);
        
        assertEquals(3, container.size(), "testAddNullElements - размер 3");
        assertTrue(container.contains(null), "testAddNullElements - содержит null");
    }
    
    public void testAddAtIndex() {
        beforeEach();
        container.add("Понедельник");
        container.add("Среда");
        container.add(1, "Вторник");
        
        assertEquals(3, container.size(), "testAddAtIndex - размер 3");
        assertEquals("Понедельник", container.get(0), "testAddAtIndex - элемент 0");
        assertEquals("Вторник", container.get(1), "testAddAtIndex - элемент 1");
        assertEquals("Среда", container.get(2), "testAddAtIndex - элемент 2");
    }
    
    public void testAddAtInvalidIndex() {
        beforeEach();
        assertThrows(() -> container.add(-1, "Элемент"), 
            "индекс для вставки", "testAddAtInvalidIndex - индекс -1");
        assertThrows(() -> container.add(2, "Элемент"), 
            "индекс для вставки", "testAddAtInvalidIndex - индекс 2");
    }
    
    public void testGetElement() {
        beforeEach();
        container.add("Иванов");
        container.add(20);
        
        assertEquals("Иванов", container.get(0), "testGetElement - элемент 0");
        assertEquals(20, container.get(1), "testGetElement - элемент 1");
    }
    
   public void testGetInvalidIndex() {
    beforeEach();
    assertThrows(() -> container.get(0), 
        "Индекс за пределами диапазона", "testGetInvalidIndex - пустой контейнер");
    
    container.add("Студент");
    assertThrows(() -> container.get(1), 
        "Индекс за пределами диапазона", "testGetInvalidIndex - индекс 1");
    assertThrows(() -> container.get(-1), 
        "Индекс за пределами диапазона", "testGetInvalidIndex - индекс -1");
}
    
    public void testRemoveByIndex() {
        beforeEach();
        container.add("Январь");
        container.add("Февраль");
        container.add("Март");
        
        Object removed = container.remove(1);
        
        assertEquals("Февраль", removed, "testRemoveByIndex - удаленный элемент");
        assertEquals(2, container.size(), "testRemoveByIndex - размер 2");
        assertEquals("Январь", container.get(0), "testRemoveByIndex - элемент 0");
        assertEquals("Март", container.get(1), "testRemoveByIndex - элемент 1");
    }
    
    public void testRemoveByValue() {
        beforeEach();
        container.add("Красный");
        container.add("Синий");
        container.add("Зеленый");
        
        boolean removed = container.remove("Синий");
        
        assertTrue(removed, "testRemoveByValue - элемент удален");
        assertEquals(2, container.size(), "testRemoveByValue - размер 2");
        assertFalse(container.contains("Синий"), "testRemoveByValue - не содержит Синий");
    }
    
    public void testRemoveNonExistent() {
        beforeEach();
        container.add("Москва");
        container.add("СПб");
        
        boolean removed = container.remove("Казань");
        
        assertFalse(removed, "testRemoveNonExistent - элемент не удален");
        assertEquals(2, container.size(), "testRemoveNonExistent - размер не изменился");
    }
    
    public void testContains() {
        beforeEach();
        container.add("Программирование");
        container.add(null);
        
        assertTrue(container.contains("Программирование"), "testContains - содержит элемент");
        assertTrue(container.contains(null), "testContains - содержит null");
        assertFalse(container.contains("Базы данных"), "testContains - не содержит элемент");
    }
    
    public void testFindIndex() {
        beforeEach();
        container.add("Яблоко");
        container.add("Банан");
        container.add("Яблоко");
        container.add(null);
        
        assertEquals(0, container.findIndex("Яблоко"), "testFindIndex - индекс Яблоко");
        assertEquals(1, container.findIndex("Банан"), "testFindIndex - индекс Банан");
        assertEquals(3, container.findIndex(null), "testFindIndex - индекс null");
        assertEquals(-1, container.findIndex("Апельсин"), "testFindIndex - не найден");
    }
    
    public void testClear() {
        beforeEach();
        container.add("Первый");
        container.add("Второй");
        container.add("Третий");
        
        container.clear();
        
        assertEquals(0, container.size(), "testClear - размер 0");
        assertTrue(container.isEmpty(), "testClear - пуст");
    }
    
    public void testToArray() {
        beforeEach();
        container.add("ФИИТ");
        container.add(2023);
        container.add(null);
        
        Object[] array = container.toArray();
        
        assertTrue(array != null, "testToArray - массив не null");
        assertEquals(3, array.length, "testToArray - длина массива 3");
        assertEquals("ФИИТ", array[0], "testToArray - элемент 0");
        assertEquals(2023, array[1], "testToArray - элемент 1");
        assertTrue(array[2] == null, "testToArray - элемент 2 null");
    }
    
    public void testDynamicExpansion() {
        ObjectContainer smallContainer = new ObjectContainer(3);
        
        for (int i = 0; i < 10; i++) {
            smallContainer.add("Группа " + i);
        }
        
        assertEquals(10, smallContainer.size(), "testDynamicExpansion - размер 10");
        assertEquals("Группа 0", smallContainer.get(0), "testDynamicExpansion - элемент 0");
        assertEquals("Группа 9", smallContainer.get(9), "testDynamicExpansion - элемент 9");
    }
    
    public void testToString() {
        beforeEach();
        assertEquals("[]", container.toString(), "testToString - пустой контейнер");
        
        container.add("Тест");
        container.add(100);
        
        String result = container.toString();
        assertTrue(result.contains("Тест"), "testToString - содержит Тест");
        assertTrue(result.contains("100"), "testToString - содержит 100");
        assertTrue(result.startsWith("["), "testToString - начинается с [");
        assertTrue(result.endsWith("]"), "testToString - заканчивается ]");
    }
    
    public void testComplexScenario() {
        beforeEach();
        container.add("Начало");
        container.add("Середина");
        container.add("Конец");
        
        container.remove(1);
        container.add(1, "Новая середина");
        container.add("Дополнение");
        
        assertEquals(4, container.size(), "testComplexScenario - размер 4");
        assertEquals("Начало", container.get(0), "testComplexScenario - элемент 0");
        assertEquals("Новая середина", container.get(1), "testComplexScenario - элемент 1");
        assertEquals("Конец", container.get(2), "testComplexScenario - элемент 2");
        assertEquals("Дополнение", container.get(3), "testComplexScenario - элемент 3");
    }
    
    @FunctionalInterface
    private interface Runnable {
        void run();
    }
}