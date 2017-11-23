package com.stackd.stackd.helpers;

/**
 * Interface for passing an object to an observer from observable
 * @param <T>
 */
public interface Consumer<T> {
    void accept(T t);
}
