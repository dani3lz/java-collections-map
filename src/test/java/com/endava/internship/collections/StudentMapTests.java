package com.endava.internship.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentMapTests {
    Student[] students;
    LocalDate[] date;
    StudentMap studentMap;
    TreeMap<Student, Integer> map;

    @BeforeEach
    void setup() {
        students = new Student[5];
        studentMap = new StudentMap();
        map = new TreeMap<>();
        date = new LocalDate[5];

        for (int i = 0; i < 5; i++) {
            date[i] = LocalDate.of(2000 + i, 4, 25);
        }

        students[0] = new Student("Abc", date[0], "skip");
        students[1] = new Student("Abg", date[1], "skip");
        students[2] = new Student("Gbb", date[2], "skip");
        students[3] = new Student("Bcv", date[3], "skip");
        students[4] = new Student("Gbb", date[4], "skip");

        for (int i = 0; i < 5; i++) {
            studentMap.put(students[i], i);
            map.put(students[i], i);
        }
    }

    @Test
    void addingANewElement_shouldReturnNull(){
        Student newStudent = new Student("Test", LocalDate.of(1998,5,22), "skip");
        Integer value = 25;
        assertAll(
                () -> assertThat(studentMap).hasSize(5),
                () -> assertThat(studentMap.put(newStudent, value)).isNull(),
                () -> assertThat(studentMap).hasSize(6)
        );
    }

    @Test
    void addingAnExistingElement_shouldRewriteItAndReturnOldValue() {
        Integer value = studentMap.get(students[3]);
        assertAll(
                () -> assertThat(studentMap).hasSize(5),
                () -> assertThat(studentMap.put(students[3], 37)).isEqualTo(value),
                () -> assertThat(studentMap).hasSize(5)
        );
    }

    @Test
    void getMethod_shouldReturnValueForInputKey() {
        assertAll(
                () -> assertThat(studentMap.get(students[0])).isZero(),
                () -> assertNull(studentMap.get(new Student("NNN", LocalDate.of(4, 4, 4), "s")))
        );
    }

    @Test
    void WhenRemovingAnElement_shouldRemoveItFromMapAndReturnValueFromRemovedKey() {
        Integer value = studentMap.get(students[2]);
        Integer oldValue = studentMap.remove(students[2]);
        assertAll(
                () -> assertThat(value).isEqualTo(oldValue),
                () -> assertNull(studentMap.get(students[2]))
        );
    }

    @Test
    void putAllMethod_shouldPutAllElementsFromSourceMapToDestinationMap() {
        TreeMap<Student, Integer> mapPutAll = new TreeMap<>();
        Student[] studentsAll = new Student[3];
        studentsAll[0] = new Student("Fgh", LocalDate.of(1980, 5, 20), "skip");
        studentsAll[1] = new Student("Gh", LocalDate.of(1988, 7, 9), "skip");
        studentsAll[2] = new Student("Cvc", LocalDate.of(1880, 10, 17), "skip");
        for (int i = 0; i < studentsAll.length; i++) {
            mapPutAll.put(studentsAll[i], i + 10);
        }
        studentMap.putAll(mapPutAll);
        map.putAll(mapPutAll);
        assertAll(
                () -> assertThat(studentMap).hasSize(8),
                () -> assertThat(studentMap).containsKey(studentsAll[0]),
                () -> assertThat(studentMap).containsKey(studentsAll[1]),
                () -> assertThat(studentMap).containsKey(studentsAll[2]),
                () -> assertThat(studentMap.keySet()).containsExactlyElementsOf(map.keySet())
        );
    }

    @Test
    void valuesMethod_ShouldReturnAllValuesFromInsertedKeys() {
        ArrayList<Integer> nrOfValues = (ArrayList<Integer>) studentMap.values();
        assertAll(
                () -> assertThat(studentMap).hasSize(5),
                () -> assertThat(studentMap.values()).containsExactlyElementsOf(map.values()),
                () -> assertThat(nrOfValues).hasSize(5)
        );
    }

    @Test
    void keySetMethod_ShouldReturnAllInsertedKeys() {
        HashSet<Student> keys = (HashSet<Student>) studentMap.keySet();
        assertAll(
                () -> assertThat(studentMap.keySet()).isEqualTo(map.keySet()),
                () -> assertThat(keys).containsOnly(students)
        );
    }

    @Test
    void containsKeyMethod_ShouldReturnTrueIfExistInsertedKey() {
        assertAll(
                () -> assertTrue(studentMap.containsKey(students[2])),
                () -> assertFalse(studentMap.containsKey(new Student("AAA", LocalDate.of(1000, 10, 10), "skip"))),
                () -> assertThat(studentMap.containsKey(students[2])).isEqualTo(map.containsKey(students[2]))
        );
    }

    @Test
    void containsValuesMethod_ShouldReturnTrueIfExistInsertedValue() {
        assertAll(
                () -> assertThat(studentMap.containsValue(3)).isEqualTo(map.containsValue(3)),
                () -> assertTrue(studentMap.containsValue(4)),
                () -> assertFalse(studentMap.containsValue(100))
        );
    }

    @Test
    void WhenInvokeIsEmptyMethod_shouldReturnFalseIfMapIsNotEmpty() {
        assertAll(
                () -> assertFalse(studentMap.isEmpty()),
                () -> assertThat(studentMap).hasSize(5)
        );
    }

    @Test
    void WhenInvokeClearMethod_shouldDeleteAllElementsInMap() {
        assertAll(
                () -> assertFalse(studentMap.isEmpty()),
                () -> studentMap.clear(),
                () -> assertTrue(studentMap.isEmpty())
        );
    }
}


