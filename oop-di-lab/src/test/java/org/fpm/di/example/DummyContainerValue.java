package org.fpm.di.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DummyContainerValue<T> {

    private boolean isValueSingleton;

    private boolean isValueInstance;

    private Class<T> key;

    private Class<? extends T> implementation;

    private T instance = null;

    private Class<?>[] constructorArguments;

    @SuppressWarnings("unchecked")
    public DummyContainerValue(Class<T> key, Object value, Class<?>[] constructorArguments,
                               boolean isValueSingleton, boolean isValueInstance) throws Exception {
        this.key = key;
        this.constructorArguments = constructorArguments;
        this.isValueSingleton = isValueSingleton;
        this.isValueInstance = isValueInstance;

        if (this.isValueInstance && this.isValueSingleton)
            throw new Exception("value can't be instance and singleton");

        if (this.isValueInstance) {
            this.instance = (T) value;
        } else {
            this.implementation = (Class<? extends T>) value;
        }
    }

    public Class<T> getKey() {
        return this.key;
    }

    @SuppressWarnings("unchecked")
    public T instantiate(List<DummyContainerValue<?>> values) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        if (this.isValueInstance) {
            return this.instance;
        } else if (this.isValueSingleton) {
            if (this.instance == null)
                this.instance = this.instantiateImplementation(values);
            return this.instance;
        } else {
            for (DummyContainerValue<?> value: values) {
                if (!value.getKey().equals(this.getKey()) && value.getKey().equals(this.implementation)) {
                    return (T) value.instantiate(values);
                }
            }

            return this.instantiateImplementation(values);
        }
    }

    @SuppressWarnings("unchecked")
    private T instantiateImplementation(List<DummyContainerValue<?>> values)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Constructor constructor = this.implementation.getConstructor(constructorArguments);
        Object[] args = new Object[constructorArguments.length];
        for (int i=0;i < constructorArguments.length; i++) {
            for (DummyContainerValue<?> value: values) {
                if (!value.getKey().equals(this.getKey()) && value.getKey().equals(constructorArguments[i])) {
                    args[i] = (T) value.instantiate(values);
                }
            }
        }

        return (T) constructor.newInstance(args);
    }
}
