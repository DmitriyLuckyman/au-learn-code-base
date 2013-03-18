package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;

/**
 * Abstract implementation of FSWalkerEventsInterface with all dummy methods
 * Use this abstract class if you didn't want to override all methods in FSWalkerEvents interface
 * User: dbandurin
 * Date: 03.03.13
 */
public class FSWalkerEventsAbstract implements FSWalkerEvents {
    public void error(File file, String message) throws IOException {}

    public void startTraverse(File rootFile) throws IOException {}

    public void endTraverse(File fromFile) throws IOException {}

    public void steppedInto(File dir, Integer totalCountOfFiles, Integer currentPosition) throws IOException {}

    public void steppedOut(File dir, Integer totalCountOfFiles, Integer currentPosition) throws IOException {}

    public void accessDeniedElement(File file) throws IOException {}

    public void handleElement(File file) throws IOException {}
}
