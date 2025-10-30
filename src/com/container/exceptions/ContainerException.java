package com.container.exceptions;

/**
 * Неконтролируемое исключение для операций с контейнером.
 * Наследуется от RuntimeException, указывает на ошибки программирования или неверное использование.
 * 
 * @author Bilovus Olga
 * @version 1.0
 */
public class ContainerException extends RuntimeException {

    /**
     * Создает исключение с указанным сообщением об ошибке.
     *
     * @param message детальное описание ошибки
     */
    public ContainerException(String message) {
        super(message);
    }

    /**
     * Создает исключение с указанным сообщением и причиной.
     *
     * @param message детальное описание ошибки
     * @param cause исходное исключение, которое вызвало данную ошибку
     */
    public ContainerException(String message, Throwable cause) {
        super(message, cause);
    }
}
