
package net.codetojoy.egg

class Composer {
    def name 
    def era
}

class CollectionsTestCase extends GroovyTestCase {

    // collect is a transformer
    void testCollect() {
        def names = [ 'Beethoven', 'Paganini', 'Bach' ]

        // test
        def composers = names.collect { n -> new Composer(name : n) } 

        assert 3 == composers.size()
        assert 'Bach' == composers[2].name
    }

    void testCollectMany() {
        def names = [ 'Beethoven', 'Paganini', 'Bach' ]
        def seededList = [new Composer(name: 'Mozart')]

        // test
        def composers = names.collectMany(seededList, { n -> [new Composer(name : n)] }) 

        assert 4 == composers.size()
    }
        
    void testFind() {
        def players = [ [name:'Randy', guitar:'Jackson'], [name:'Eddie', guitar:'Frankenstrat'], [name:'Slash', guitar:'Les Paul'] ]

        // test
        def result = players.find { p -> p['guitar'] == 'Jackson' }

        assert 'Randy' == result['name']
    }

    void testFindAll() {
        def players = [ [name:'Randy', guitar:'Les Paul'], [name:'Eddie', guitar:'Frankenstrat'], [name:'Slash', guitar:'Les Paul'] ]

        // test
        def result = players.findAll { p -> p['guitar'] == 'Les Paul' } 

        assert 'Randy' == result[0]['name']
        assert 'Slash' == result[1]['name']
    }
    
    void testFlatten() {
        def lists = [ [1], [2,3,4], [5], [] ]

        // test
        def result = lists.flatten()

        assert 5 == result.size()
    }
    
    void testGroupBy() {
        def composers = []
        composers << new Composer(name : 'Beethoven', era : 'Romantic')
        composers << new Composer(name : 'Mozart', era : 'Classical')
        composers << new Composer(name : 'Chopin', era : 'Romantic')
        composers << new Composer(name : 'Haydn', era : 'Classical')

        // test
        def composerMap = composers.groupBy { c -> c.era }

        assert 2 == composerMap.keySet().size()
        assert 2 == composerMap['Classical'].size()
        assert 2 == composerMap['Romantic'].size()
    }
        
    // inject is an accumulator
    void testInject_AsSum() {
        def nums = [1, 3, 5, 7, 9]

        // test
        // Note the closure implicitly returns the expression, which is fed back in as the new value
        // for val on next iteration
        // Note: there is a sum() method on collections. This is an easy example of inject()
        def result = nums.inject(0, { val, item -> val += item } )

        assert 25 == result
    }
    
    // inject is an accumulator
    void testInject_AsAppender() {
        def names = [ 'Beethoven', 'Mozart' ]

        // test
        // Note the closure implicitly returns the expression, which is fed back in as the new value
        // for val on next iteration
        def result = names.inject("<composers>", { val, item -> val += "<name>$item</name>" } )
        result += "</composers>"

        assert "<composers><name>Beethoven</name><name>Mozart</name></composers>" == result
    }
    
    void testJoin() {
        def list = [100, 67, 24, 20]

        // test
        def s = list.join(".")

        assert "100.67.24.20" == s
    }
    
    void testSort() {
        def composers = []
        composers << new Composer(name : 'Beethoven', era : 'Romantic')
        composers << new Composer(name : 'Mozart', era : 'Classical')
        composers << new Composer(name : 'Chopin', era : 'Romantic')
        composers << new Composer(name : 'Haydn', era : 'Classical')

        // test
        def sortedList = composers.sort { c -> c.name }

        assert "Beethoven" == sortedList[0].name
        assert "Chopin" == sortedList[1].name
        assert "Mozart" == sortedList[3].name
    }

    void testFindResults() {
        def composers = []
        composers << new Composer(name : 'Beethoven', era : 'Romantic')
        composers << new Composer(name : 'Mozart', era : 'Classical')
        composers << new Composer(name : 'Chopin', era : 'Romantic')
        composers << new Composer(name : 'Haydn', era : 'Classical')

        // test
        def results = composers.findResults { c -> 
            (c.era == 'Classical') ? c.name : null 
        }

        assert "Mozart" == results[0]
        assert "Haydn" == results[1]
    }
}
