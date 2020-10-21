def list = ["s1", "s2", "s3:", "s4", "s5"]

def res = list.inject([], { acc, item ->
    def lastItem = acc.isEmpty() ? null : acc.last()

    if (lastItem?.endsWith(":")) {
        acc[-1] += item
    } else {
        acc << item
    }

    acc
}) 

assert res == ["s1", "s2", "s3:s4", "s5"]

