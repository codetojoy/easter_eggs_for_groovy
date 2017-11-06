
def respStr1 = '''
[
      {
      "name": "ABCD",
      "user": "TEMP_USER",
      "description": null,
      "createdtime": "2017-11-06 11:11:18",
      "groupname": "XYZ",
      "tempId": 101998,
   },
{
      "name": "EFGH",
      "user": "TEMP_USER",
      "description": null,
      "createdtime": "2017-11-05 11:11:18",
      "groupname": "XYZ",
      "tempId": 101999,
   }
]
'''

def respStr2 = '''
[
      {
      "name": "ABCD",
      "user": "TEMP_USER",
      "description": null,
      "createdtime": "2017-11-06 11:11:18",
      "groupname": "XYZ",
      "tempId": 101998,
   },
{
      "name": "EfGH",
      "user": "TEMP_USER",
      "description": null,
      "createdtime": "2017-11-05 11:11:18",
      "groupname": "XYZ",
      "tempId": 101999,
   }
]
'''

def buildSortedList = { items ->
    def list = []
    list.addAll(items)
    Collections.sort(list)
    list
}

def buildUserInfo = { user ->
    def result = new Expando()
    result.keys = buildSortedList(user.keySet())
    result.values = []
    result.keys.each { key -> result.values << user[key] }
    result
}

def compareUser = { userInfo1, userInfo2 ->
    (userInfo1.keys == userInfo2.keys) && (userInfo1.values == userInfo2.values)
}

def compareUsers = { resp1, resp2 ->
    def result = true

    if (resp1.size() == resp2.size()) {
        // assumes that the lists are sorted !!
        [resp1, resp2].transpose().each {
            def user1 = it[0]
            def user2 = it[1]
            if (result) {
                result = compareUser(user1, user2)
            }
        }
    } else { 
        result = false
    } 

    result
}

def resp1 = new groovy.json.JsonSlurper().parseText(respStr1)
def resp2 = new groovy.json.JsonSlurper().parseText(respStr2)

assert compareUsers(resp1, resp2)

println 'Ready.'
