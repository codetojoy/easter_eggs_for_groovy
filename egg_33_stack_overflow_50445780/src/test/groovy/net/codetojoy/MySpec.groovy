
package net.codetojoy;

import spock.lang.*

class MySpec extends Specification {

    def "canary"() {
        when:
            def result = 2 + 2
        
        then:
            4 == result
    }
}
