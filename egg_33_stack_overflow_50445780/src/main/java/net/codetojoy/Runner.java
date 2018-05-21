
package net.codetojoy;

public class Runner {
    public static void main(String[] args) {
        SimpleConverter simpleConverter = new SimpleConverter();
        Integer x = simpleConverter.convert("5150");
        System.out.println("TRACER x: " + x);

        MyConverter myConverter = new MyConverter<String,Integer>();
        myConverter.example = 5150;
        System.out.println("TRACER myC : " + myConverter.convert("id"));

        System.out.println("TRACER runner ready.");
    }
}
