package org.fpm.di.example;
import org.fpm.di.Container;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DummyContainer implements Container {

    private List<DummyContainerValue<?>> bindings;

    public DummyContainer(List<DummyContainerValue<?>> bindings) {
        this.bindings = bindings;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        for (DummyContainerValue<?> value: this.bindings) {
            if (value.getKey().equals(clazz)) {
                try {
                    return (T) value.instantiate(this.bindings);
                } catch (NoSuchMethodException | IllegalAccessException |
                        InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return null;
    }
}
