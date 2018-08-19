
def map = [
    customer: ['Clinton', 'Clinton', 'Mark', 'Antony', 'Clinton', 'Mark'],
    price: [15000.0, 27000.0, 28000.0, 56000.0, 21000.0, 61000.0]
]

def getSumMap = { data ->
    def result = [:].withDefault { c -> 0.0 }

    [data.customer, data.price].transpose().each { c, p -> result[c] += p }

    result
}

assert 63000.0 == getSumMap(map)['Clinton']
assert 89000.0 == getSumMap(map)['Mark']
assert 56000.0 == getSumMap(map)['Antony']
