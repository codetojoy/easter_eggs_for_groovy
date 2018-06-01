
class Copy { 
    def copy = { a, b -> println "TRACER copy ${a} to ${b}" }
    def copyGroovy = { p1, p2 -> copy(p1,p2) }
    def copyFixP1 = { p1 -> copyGroovy.curry(p1) }
}

class Copier {
    def context 
    def p1, p2

    def Copier(def context) {
        this.context = context
    }

    def methodMissing(String name, args) {
        p1 = args[0]
        return this
    }

    Object getProperty( String property ) {
        p2 = context.getProperty(property)
        context.getProperty("copy").copyFixP1(p1)(p2)
    }
}

// ----- main 

class Example {
    def src = "A"
    def dest = "B"
    def copy = new Copy()

    def run = { ->
        def cp = new Copier(this)

        cp src dest
    }
}

new Example().run()

