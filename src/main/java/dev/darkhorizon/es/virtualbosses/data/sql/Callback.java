package dev.darkhorizon.es.virtualbosses.data.sql;

public interface Callback<T> {
    void onQueryDone(T type);
}