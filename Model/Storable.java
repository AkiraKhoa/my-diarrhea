package model;

import java.io.IOException;
import java.util.List;

public interface Storable<T> {
    List<T> loadAll(String filePath) throws IOException, ClassNotFoundException;
    void saveAll(List<T> items, String filePath) throws IOException;
}
