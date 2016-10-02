
package net.codetojoy.egg.domain

class PersonTestCase extends GroovyTestCase {
    void testPersonCanCompose() {
        def person = new Person(name: "Beethoven", era: "Romantic")

        // test
        def result = person.compose()

        assertTrue result == "can compose music"
    }

    void testPersonCanPlay() {
        def person = new Person(name: "Beethoven", era: "Romantic")

        // test
        def result = person.play()

        assertTrue result == "can play music"
    }
}
