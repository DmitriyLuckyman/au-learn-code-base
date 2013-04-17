package ru.spbau.bandurin.task7;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Test main class for serializer Deserializer using reflection.
 */
public class Main {
    /**
     * Main method dserialize student from file increase avg property and serialize student to file.
     * @param args first parameter should be path to properties file
     * @throws IOException if can't read or write to file
     * @throws InvocationTargetException if can't call setter or getter method while process
     * @throws IllegalAccessException if access violation when call setter or getter method while process
     * @throws InstantiationException if can't create instance of deserialied object
     */
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if(args.length != 1){
            throw new RuntimeException("Wrong command line.Programm has only one argument - path to property file");
        }
        File file = new File(args[0]);
        ReflectionDeSerializer deSerializer = new ReflectionDeSerializer();
        final Student student = deSerializer.deserialize(Student.class, file);
        if(student.getAvgGrade() + 1.0 <= 5.0){
            student.setAvgGrade(student.getAvgGrade() + 1.0);
        }
        ReflectionSerializer serializer = new ReflectionSerializer();
        serializer.serialize(student, file);
    }
}
