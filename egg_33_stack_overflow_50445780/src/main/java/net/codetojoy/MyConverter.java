
package net.codetojoy;

import org.modelmapper.*;

public class MyConverter<A,B> extends AbstractConverter<A,B> {
    public B example;

    @Override
    public B convert (A source) {
        B result = null;

        if (source != null) {
            result = example;
        }

        return result;
    }
}

