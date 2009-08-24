
package net.codetojoy.egg

class RegExTestCase extends GroovyTestCase {

    def str = "Jenny 867- 5309"
    
    void testSimpleMatch() {
        // test
        def m = (str =~ /.*Jen.*/)
        // post
        assert true == m.matches()
    }

    void testCaseInsensitive() {
        // test
        def m = (str =~ /^(?i)jenny.*/)
        // post
        assert true == m.matches()
    }

    void testGroups() {
        // test
        def m = (str =~ /.*(\d\d\d)\s*-\s*(\d\d\d\d).*/)
        // post
        assert "867" == m[0][1]
        assert "5309" == m[0][2]
    }
    
}