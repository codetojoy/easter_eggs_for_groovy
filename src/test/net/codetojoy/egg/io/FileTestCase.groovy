
package net.codetojoy.egg

class FileTestCase extends GroovyTestCase {
    
    void testWritingChinese() {
        // pre
        def niHao = "\u4F60\u597D" // Chinese greeting
        def file = new File('data/testWritingChinese.txt')
        file.delete()        
        // test
        file.withWriter('UTF-8', { f -> f.append(niHao) } )
        // post
        assert file.exists()
        def checkFile = new File('data/testWritingChinese.txt')
        def text = checkFile.getText('UTF-8')
        assert niHao == text
    }
    
    void testFindFileInSubDirectories() {
        // pre
        def targetFile = 'FileTestCase.groovy'
        def start = new File('.')
        def result = ""
        // test        
        start.eachFileRecurse { file -> 
            if (file.name == targetFile) {
               result = file 
            }
        }
        // post
        assert result.name == targetFile
        assert result.exists()
    }
}