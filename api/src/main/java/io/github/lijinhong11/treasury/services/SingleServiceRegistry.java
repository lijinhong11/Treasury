package io.github.lijinhong11.treasury.services;

public sealed interface SingleServiceRegistry<T> permits ChatServiceRegistry, PermissionServiceRegistry {
    void register(T provider);

    boolean isRegistered();

    T get();
}