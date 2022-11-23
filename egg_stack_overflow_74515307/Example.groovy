
def fakeList = [
                [coverageType: 'health', amount: 9, expireDate: 2020], 
                [coverageType: 'insurance', amount: 10, expireDate: 2020], 
                [coverageType: 'health', amount: 9, expireDate: 2021],
            ] 

def groupList = fakeList.groupBy { it.coverageType }.collect { coverageType, items ->
    def map = [:]
    map.'parent' = coverageType 
    map.'childs' = items.collect { item ->
        def childMap = [:]
        childMap.'coverage' = coverageType
        childMap.'amount' = item.amount as String
        childMap.'expireDate' = item.expireDate as String
        childMap
    }
    map
}

println "groupList:"
println groupList

import static groovy.json.JsonOutput.*
println "as JSON:"
println prettyPrint(toJson(groupList))
    
