package dev.darkhorizon.es.black.items;

public class ItemListItems {
    private static ItemListItems INSTANCE = null;

    private ItemListItems() {
        //TODO Singleton for only 1 object instance
    }

    public static ItemListItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(ItemListItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemListItems();
                }
            }
        }
    }
}
