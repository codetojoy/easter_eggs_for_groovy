
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
        def userPairs = [resp1, resp2].transpose()
        // def userInfos1 = resp1.collect { buildUserInfo(it) }
        // def userInfos2 = resp2.collect { buildUserInfo(it) }
        // def userInfoPairs = [userInfos1, userInfos2].transpose()
        userPairs.each { pair ->
            def user1 = pair[0]
            def user2 = pair[1]
            if (result) {
                result = compareUser(pair[0], pair[1])
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
