
import groovy.xml.*

// build a list of parsed XML files

def composers = []

new File(".").eachFile { def file ->
    if (file.name ==~ /.*\.xml/) {
        composers << new XmlSlurper().parse(file)
    }
}

// build XML document
// e.g. 
// <root>
//     <composer><name>Wolfgang Mozart</name> ...</composer>
//     <composer><name>JS Bach</name> ...</composer>
// </root>

def xml = new StreamingMarkupBuilder().bind {
    root {
        composers.each { c ->
            mkp.yield c
        }
    }
}.toString()

println XmlUtil.serialize(xml)
