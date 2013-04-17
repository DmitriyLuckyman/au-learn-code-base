package ru.spbau.bandurin.task7;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

/**
 * DeSerializer based on reflection used only public setters with one parameter.
 * @author Dmitriy Bandurin
 *         Date: 08.04.13
 */
public class ReflectionDeSerializer {

    /**
     *  Deseroalize object of T type from input file
     * @param clazz class of output object
     * @param inputFile file with serialized object
     * @param <T> output object type
     * @return deserialized object
     * @throws InvocationTargetException if can't call setter method
     * @throws IllegalAccessException if access violation to setter method
     * @throws IOException if can;t read from input file
     * @throws InstantiationException If can't create instance of T type.
     */
    public <T> T deserialize(final Class<T> clazz, final File inputFile) throws InvocationTargetException, IllegalAccessException, IOException, InstantiationException {
        if(clazz == null || inputFile == null){
            throw new NullPointerException("clazz or inputFile can't be null");
        }
        Properties pr = loadProperties(inputFile);
        final T t = clazz.newInstance();
        for (Method method : clazz.getDeclaredMethods()) {
            final int modifiers = method.getModifiers();
            final String name = method.getName();
            if(Modifier.isPublic(modifiers) && name.startsWith("set") && method.getParameterTypes().length == 1){
                final String propertyName = getPropertyName(name);
                if(pr.containsKey(propertyName)){
                    final String valueAsString = pr.getProperty(propertyName, null);
                    final Class<?> inputType = method.getParameterTypes()[0];
                    Object value = null;
                    if(Long.TYPE.equals(inputType) || Long.class.equals(inputType)){
                        value = Long.valueOf(valueAsString);
                    } else if(Integer.TYPE.equals(inputType) || Integer.class.equals(inputType)){
                        value = Integer.valueOf(valueAsString);
                    } else if(Double.TYPE.equals(inputType) || Double.class.equals(inputType)){
                        value = Double.valueOf(valueAsString);
                    } else if(Float.TYPE.equals(inputType) || Float.class.equals(inputType)){
                        value = Float.valueOf(valueAsString);
                    }  else if(Byte.TYPE.equals(inputType) || Byte.class.equals(inputType)){
                        value = Byte.valueOf(valueAsString);
                    } else if(Character.TYPE.equals(inputType) || Character.class.equals(inputType)){
                        value = valueAsString.charAt(0);
                    } else if(Boolean.TYPE.equals(inputType) || Boolean.class.equals(inputType)){
                        value = Boolean.valueOf(valueAsString);
                    } else if(Short.TYPE.equals(inputType) || Short.class.equals(inputType)){
                        value = Short.valueOf(valueAsString);
                    } else if(String.class.equals(inputType)){
                        value = valueAsString;
                    }
                    method.invoke(t, value);
                }
            }
        }
        return t;
    }

    private Properties loadProperties(final File inputFile) throws IOException {
        final Properties pr = new Properties();
        FileInputStream inStream = null;
        try{
            inStream = new FileInputStream(inputFile);
            pr.load(inStream);
        } finally {
            if(inStream != null){
                try{
                    inStream.close();
                } catch (Exception e){
                    System.out.println("Strange exception while close file :" + e.getMessage());
                }
            }
        }
        return pr;
    }

    private String getPropertyName(String name) {
        return name.substring(3, 4).toLowerCase() + name.substring(4);
    }
}