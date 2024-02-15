package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
   private int size = 0;
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

   public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (size == 0) {
            return null;
        }
        for (int i = 0; i < size-1; i++) {
            if (storage[i].getUuid().equals(uuid)) {
            return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                storage[i] = storage[size - 1];
                storage[i] = null;
                size--;
            }
        }
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        System.out.println(size);
        if (size == 0) {
            return new Resume[size];
        }
        Resume[] newResume = Arrays.copyOf(storage, size);
        return newResume;
    }

    public int size() {
        return size;
    }
}
