
package net.codetojoy.egg

class MyFileTestCase extends GroovyTestCase {
        
    def phraseFile = new File("data/Phrase.txt")
    
    void testFileBinaryCopy() {
        def targetFile = new File("data/PhraseCopy.txt")
        targetFile.delete()

        // test
        def myFile = new MyFile()
        myFile.fastBinaryCopy(phraseFile, targetFile)

        assert targetFile.exists()
        assert phraseFile.size() == targetFile.size()
    }
}