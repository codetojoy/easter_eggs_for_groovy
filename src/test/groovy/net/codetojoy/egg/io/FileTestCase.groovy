
package net.codetojoy.egg

class FileTestCase extends GroovyTestCase {
        
    def phraseFile = new File("data/Phrase.txt")
    
    void testFileCopy() {
        def targetFile = new File("data/PhraseCopy.txt")
        targetFile.delete()

        // test
        targetFile.withWriter { it.write( phraseFile.getText() ) }

        assert targetFile.exists()
        assert phraseFile.size() == targetFile.size()
    }
    
    void testWritingChinese() {
        def niHao = "\u4F60\u597D" // Chinese greeting
        def file = new File('data/testWritingChinese.txt')
        file.delete()        

        // test
        file.withWriter('UTF-8', { f -> f.append(niHao) } )

        assert file.exists()
        def checkFile = new File('data/testWritingChinese.txt')
        def text = checkFile.getText('UTF-8')
        assert niHao == text
    }
    
    void testFindFileInSubDirectories() {
        def targetFile = 'FileTestCase.groovy'
        def start = new File('.')
        def result = ""
 
        // test        
        start.eachFileRecurse { file -> 
            if (file.name == targetFile) {
               result = file 
            }
        }

        assert result.name == targetFile
        assert result.exists()
    }
}