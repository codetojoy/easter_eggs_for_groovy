
package net.codetojoy.egg

class MyFile {
    def fastBinaryCopy = {  sourceFile, destFile ->
        def ic = null
        def oc = null

        try {
            ic = new FileInputStream(sourceFile).getChannel()
            oc = new FileOutputStream(destFile).getChannel()
            ic.transferTo(0, ic.size(), oc)
        } finally {
            if (ic) { ic.close() }
            if (oc) { oc.close() }
        }
    }    
}