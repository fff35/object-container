package com.container;

import com.container.exceptions.ContainerException;
import java.util.Objects;

/**
 * Универсальный контейнер для хранения объектов разных типов.
 * Реализует динамический массив с автоматическим расширением.
 * Поддерживает основные операции: добавление, удаление, поиск, доступ по индексу.
 * 
 * @author Bilovus olga
 * @version 1.0
 */
public class ObjectContainer {
    
    /**
     * Внутренний массив для хранения элементов
     */
    private Object[] elements;
    
    /**
     * Текущее количество элементов
     */
    private int size;
    
    /**
     * Начальная емкость по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Коэффициент увеличения размера
     */
    private static final double GROW_FACTOR = 1.5;
    
    /**
     * Создает контейнер с емкостью по умолчанию.
     */
    public ObjectContainer() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Создает контейнер с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость контейнера
     * @throws ContainerException если initialCapacity меньше или равно 0
     */
    public ObjectContainer(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new ContainerException(
                "Начальная емкость должна быть положительным числом: " + initialCapacity
            );
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }
    
    /**
     * Добавляет элемент в конец контейнера.
     *
     * @param element элемент для добавления
     * @return true если элемент успешно добавлен
     */
    public boolean add(Object element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        return true;
    }
    
    /**
     * Вставляет элемент в указанную позицию.
     *
     * @param index позиция для вставки 
     * @param element элемент для вставки
     * @throws ContainerException если индекс недопустим
     */
    public void add(int index, Object element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        
        // Сдвигаем элементы вправо
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }
    
    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws ContainerException если индекс недопустим
     */
    public Object get(int index) {
        checkIndex(index);
        return elements[index];
    }
    
    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     * @throws ContainerException если индекс недопустим
     */
    public Object remove(int index) {
        checkIndex(index);
        
        Object removed = elements[index];
        
        // Сдвигаем элементы влево
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        
        elements[size - 1] = null;
        size--;
        return removed;
    }
    
    /**
     * Удаляет первое вхождение указанного элемента.
     *
     * @param element элемент для удаления
     * @return true если элемент был найден и удален
     */
    public boolean remove(Object element) {
        int index = findIndex(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }
    
    /**
     * Проверяет наличие элемента в контейнере.
     *
     * @param element искомый элемент
     * @return true если элемент найден
     */
    public boolean contains(Object element) {
        return findIndex(element) != -1;
    }
    
    /**
     * Ищет индекс первого вхождения элемента.
     *
     * @param element искомый элемент
     * @return индекс элемента или -1 если не найден
     */
    public int findIndex(Object element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Возвращает текущее количество элементов.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }
    
    /**
     * Проверяет, пуст ли контейнер.
     *
     * @return true если контейнер пуст
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Очищает контейнер, удаляя все элементы.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    
    /**
     * Возвращает массив со всеми элементами контейнера.
     *
     * @return массив элементов
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements[i];
        }
        return result;
    }
    
    /**
     * Гарантирует достаточную емкость контейнера.
     * Увеличивает внутренний массив при необходимости.
     *
     * @param minCapacity минимальная требуемая емкость
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = (int)(elements.length * GROW_FACTOR);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }
    }
    
    /**
     * Проверяет корректность индекса для операций get и remove.
     *
     * @param index проверяемый индекс
     * @throws ContainerException если индекс невалиден
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ContainerException(
                "Индекс за пределами диапазона: " + index + ". Размер контейнера: " + size
            );
        }
    }
    
    /**
     * Проверяет корректность индекса для операции add.
     *
     * @param index проверяемый индекс
     * @throws ContainerException если индекс невалиден
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ContainerException(
                "Недопустимый индекс для вставки: " + index + ". Допустимый диапазон: 0 - " + size
            );
        }
    }
    
    /**
     * Возвращает строковое представление контейнера.
     *
     * @return строковое представление в формате [элемент1, элемент2, ...]
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < size; i++) {
            result.append(elements[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
