
package net.codetojoy;

import spock.lang.*

class SimpleConverterSpec extends Specification {

    def "canary"() {
        given:
            def simpleConverter = new SimpleConverter()

        when:
            def result = simpleConverter.convert("6160")
        
        then:
            6160 == result
    }

    def "mock example"() {
        given:
            def simpleConverter = Mock(SimpleConverter.class)

        when:
            def result = simpleConverter.convert("6160")
        
        then:
            1 * simpleConverter.convert("6160")
    }
}
