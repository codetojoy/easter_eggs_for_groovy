package net.codetojoy;

import org.modelmapper.*;

public class SimpleConverter extends AbstractConverter<String,Integer> {
    @Override
    public Integer convert (String source) {
        return Integer.parseInt(source);
    }
}
