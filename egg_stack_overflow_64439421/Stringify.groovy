def list = ["s1", "s2", "s3:", "s4", "s5"]

def str = list.join(",")
// e.g. s1,s2,s3:,s4,s5

str = str.replaceAll(/\:,/, ":")
// e.g. s1,s2,s3:s4,s5

def res = str.split(",")
assert res == ["s1", "s2", "s3:s4", "s5"]

println res
println "Ready."
