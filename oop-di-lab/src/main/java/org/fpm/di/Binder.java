package org.fpm.di;

public interface Binder {
    <T> void bind(Class<T> clazz) throws Exception;

    <T> void bind(Class<T> clazz, Class<? extends T> implementation) throws Exception;

    <T> void bind(Class<T> clazz, T instance) throws Exception;
}
