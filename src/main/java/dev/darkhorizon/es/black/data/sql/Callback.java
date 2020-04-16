package dev.darkhorizon.es.black.data.sql;

public interface Callback<T> {
    void onQueryDone(T type);
}