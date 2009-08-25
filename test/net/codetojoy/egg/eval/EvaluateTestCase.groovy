
package net.codetojoy.egg.eval

class XMLUtil {
    def wrapTag(def s) { "<exampleTag>$s</exampleTag>" }
}

class EvaluateTestCase extends GroovyTestCase {

    void testEval_Basic() {
        // pre
        def binding = new Binding()
        def s = "[ name : 'Joe Satriani', instrument : 'guitar' ]"
        def shell = new GroovyShell(binding)
        // test
        def map = shell.evaluate(s)        
        // post
        assert 'Joe Satriani' == map['name']
    }
    
    void testEval_SetVar() {
        // pre
        def binding = new Binding()
        binding.setVariable("tagger", new XMLUtil())
        def s = "tagger.wrapTag('code to joy')"
        def shell = new GroovyShell(binding)
        // test
        def tag = shell.evaluate(s)        
        // post
        assert "<exampleTag>code to joy</exampleTag>" == tag
    }
}