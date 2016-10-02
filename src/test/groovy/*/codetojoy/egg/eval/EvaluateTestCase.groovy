
package net.codetojoy.egg.eval

class XMLUtil {
    def wrapTag(def s) { "<exampleTag>$s</exampleTag>" }
}

class EvaluateTestCase extends GroovyTestCase {

    void testEval_Basic() {
        def binding = new Binding()
        def s = "[ name : 'Joe Satriani', instrument : 'guitar' ]"
        def shell = new GroovyShell(binding)

        // test
        def map = shell.evaluate(s)        

        assert 'Joe Satriani' == map['name']
    }
    
    void testEval_SetVar() {
        def binding = new Binding()
        binding.setVariable("tagger", new XMLUtil())
        def s = "tagger.wrapTag('code to joy')"
        def shell = new GroovyShell(binding)

        // test
        def tag = shell.evaluate(s)        

        assert "<exampleTag>code to joy</exampleTag>" == tag
    }
}