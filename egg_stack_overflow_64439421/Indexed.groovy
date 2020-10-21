def list = ["s1", "s2", "s3:", "s4", "s5"]

def index = list.indexed()

def res = list.collect { item -> 
    def myIndex = index.find{ k, v -> v == item }.key
    def lastItem = (myIndex > 0) ? list[myIndex - 1] : null
    (lastItem?.endsWith(":")) ? lastItem + item : item 
}.findAll { !(it ==~ /.*:/) }

assert res == ["s1", "s2", "s3:s4", "s5"]
println "Ready."
