package com.heritage.heritagemacedonia.Impl;

public interface Filter<T> {
    T execute(T input, T attributeName);
}
