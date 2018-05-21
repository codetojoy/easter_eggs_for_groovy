
package net.codetojoy;

import spock.lang.*

class MyConverterSpec extends Specification {

    def "canary"() {
        given:
            def myConverter = new MyConverter<String,Integer>()
            myConverter.example = 6160

        when:
            def result = myConverter.convert("id")
        
        then:
            6160 == result
    }

    def "mock example"() {
        given:
            def myConverter = Mock(MyConverter.class)

        when:
            def result = myConverter.convert("id")
        
        then:
            1 * myConverter.convert("id")
    }
}
