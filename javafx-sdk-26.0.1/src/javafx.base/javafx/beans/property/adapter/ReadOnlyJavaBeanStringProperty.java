/*
 * Copyright (c) 2011, 2024, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javafx.beans.property.adapter;

import com.sun.javafx.property.MethodHelper;
import com.sun.javafx.property.adapter.Disposer;
import com.sun.javafx.property.adapter.ReadOnlyPropertyDescriptor;
import javafx.beans.property.ReadOnlyStringPropertyBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * A {@code ReadOnlyJavaBeanStringProperty} provides an adapter between a regular
 * read only Java Bean property of type {@code String} and a JavaFX
 * {@code ReadOnlyStringProperty}. It cannot be created directly, but a
 * {@link ReadOnlyJavaBeanStringPropertyBuilder} has to be used.
 * <p>
 * As a minimum, the Java Bean class must implement a getter for the
 * property.
 * The class, as well as the getter method, must be declared public.
 * If the getter of an instance of this class is called, the property of
 * the Java Bean is returned. If the Java Bean property is bound (i.e. it supports
 * PropertyChangeListeners), this {@code ReadOnlyJavaBeanStringProperty} will be
 * aware of changes in the Java Bean. Otherwise it can be notified about
 * changes by calling {@link #fireValueChangedEvent()}.
 * </p>
 * <p><b>Deploying an Application as a Module</b></p>
 * <p>
 * If the Java Bean class is in a named module, then it must be reflectively
 * accessible to the {@code javafx.base} module.
 * A class is reflectively accessible if the module
 * {@link Module#isOpen(String,Module) opens} the containing package to at
 * least the {@code javafx.base} module.
 * </p>
 * <p>
 * For example, if {@code com.foo.MyBeanClass} is in the {@code foo.app} module,
 * the {@code module-info.java} might
 * look like this:
 * </p>
 *
<pre>{@code module foo.app {
    opens com.foo to javafx.base;
}}</pre>
 *
 * <p>
 * Alternatively, a class is reflectively accessible if the module
 * {@link Module#isExported(String) exports} the containing package
 * unconditionally.
 * </p>
 *
 * @see javafx.beans.property.ReadOnlyStringProperty
 * @see ReadOnlyJavaBeanStringPropertyBuilder
 * @since JavaFX 2.1
 */
public final class ReadOnlyJavaBeanStringProperty extends ReadOnlyStringPropertyBase implements ReadOnlyJavaBeanProperty<String> {

    private final ReadOnlyPropertyDescriptor<String> descriptor;
    private final ReadOnlyPropertyDescriptor<String>.ReadOnlyListener listener;

    ReadOnlyJavaBeanStringProperty(ReadOnlyPropertyDescriptor<String> descriptor, Object bean) {
        this.descriptor = descriptor;
        this.listener = descriptor.new ReadOnlyListener(bean, this);
        descriptor.addListener(listener);
        Disposer.addRecord(this, new DescriptorListenerCleaner<>(descriptor, listener));
    }

    /**
     * {@inheritDoc}
     *
     * @throws UndeclaredThrowableException if calling the getter of the Java Bean
     * property throws an {@code IllegalAccessException} or an
     * {@code InvocationTargetException}.
     */
    @Override
    public String get() {
        try {
            return (String)MethodHelper.invoke(descriptor.getGetter(), getBean(), (Object[])null);
        } catch (IllegalAccessException e) {
            throw new UndeclaredThrowableException(e);
        } catch (InvocationTargetException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getBean() {
        return listener.getBean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return descriptor.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fireValueChangedEvent() {
        super.fireValueChangedEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        descriptor.removeListener(listener);
    }
}
