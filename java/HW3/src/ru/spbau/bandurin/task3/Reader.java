package ru.spbau.bandurin.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:31
 */
public class Reader extends BufferedReader {

    public Reader(java.io.Reader in) {
        super(in);
    }

    public List<String> getExpressionList() throws IOException {
        List<String> expressions = new ArrayList<String>();
        String expression;
        while((expression = readLine()) != null){
            expressions.add(expression);
        }
        return expressions;
    }

    @Override
    public String readLine() throws IOException {
        String s = super.readLine();
        if(s != null){
            s = s.replaceAll("[ \t]*", "");
        }
        return s;
    }
}
