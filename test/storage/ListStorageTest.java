package storage;

import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStorageTest {

    Storage storage = new ListStorage();
    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final String UUID_4 = "uuid4Test";
    protected final Resume RESUME1 = new Resume(UUID_1, "Name");
    protected final Resume RESUME2 = new Resume(UUID_2, "Name");
    protected final Resume RESUME3 = new Resume(UUID_3, "Name");
    protected final Resume RESUME4 = new Resume(UUID_4, "Name");

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }
    @Test
    void initialSize() {
        assertSize(3);
    }

    @Test
    void save() {
        storage.save(RESUME4);
        assertGet(RESUME4);
        storage.size();
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

    @Test
    void updateIfNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME4));
    }

    @Test
    void updateIfExist() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME1.getUuid()));
        assertSize(2);
    }

    @Test
    void get() {
        assertGet(RESUME1);
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(RESUME4.getUuid()));
    }

    @Test
    void getAll() {
        assertEquals(storage.size(), storage.getAllSorted().size());
    }
}