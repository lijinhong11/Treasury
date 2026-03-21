package io.github.lijinhong11.treasury.services;

@Deprecated
public interface SingleServiceRegistry<T> {
    void register(T provider);

    boolean isRegistered();

    T get();
}