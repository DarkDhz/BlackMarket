package dev.darkhorizon.es.black.Data.sql;

public interface Callback<T> {
    void onQueryDone(T type);
}