package ru.spbau.bandurin.task7;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

/**
 * Serializer based on reflection used only public getters without parameters.
 * @author Dmitriy Bandurin
 *         Date: 08.04.13
 */
public class ReflectionSerializer {
    /**
     * Serialize object to outputFile
     * @param object object to serialize
     * @param outputFile output file for serialization
     * @throws InvocationTargetException if can't invoke getter for any property
     * @throws IllegalAccessException if access violation when get property from object appears
     * @throws IOException if any when store properties to file
     */
    public void serialize(final Object object, final File outputFile) throws InvocationTargetException, IllegalAccessException, IOException {
        if(object == null || outputFile == null){
            throw new NullPointerException("object or outputFile can't be null");
        }
        Properties pr = new Properties();
        for (Method method : object.getClass().getDeclaredMethods()) {
            final int modifiers = method.getModifiers();
            final String name = method.getName();
            if(Modifier.isPublic(modifiers) && name.startsWith("get") && method.getParameterTypes().length == 0){
                final Object value = method.invoke(object);
                if(value != null){
                    pr.setProperty(getPropertyName(name), value.toString());
                }
            }
        }
        storeProperties(outputFile, pr);
    }

    private void storeProperties(File outputFile, Properties pr) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFile);
            pr.store(out, "");
        } finally {
            if(out != null){
                out.close();
            }
        }
    }

    private String getPropertyName(String name) {
        return name.substring(3, 4).toLowerCase() + name.substring(4);
    }
}
