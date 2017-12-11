
import org.junit.*
import static org.junit.Assert.*

class GameMatrixTestCase {
    def dot = "∘"
    def gameMatrix
    def canonicals = [:]

    def buildGameMatrix(Integer... list) {
        assert 8 == list.size()
        def t1 = new PlayerTuple(list[0], list[1])
        def t2 = new PlayerTuple(list[2], list[3])
        def t3 = new PlayerTuple(list[4], list[5])
        def t4 = new PlayerTuple(list[6], list[7])
        new GameMatrix(t1, t2, t3, t4)
    }

    @Test
    void testColExchange() {
        gameMatrix = buildGameMatrix(2,2,3,4,
                                     1,1,4,3) 
        
        // test
        def result = gameMatrix.colExchange()

        assert buildGameMatrix(3,4,2,2,
                               4,3,1,1) == result
    }

    @Test
    void testRowExchange() {
        gameMatrix = buildGameMatrix(2,2,3,4,
                                     1,1,4,3) 
        
        // test
        def result = gameMatrix.rowExchange()

        assert buildGameMatrix(1,1,4,3,
                               2,2,3,4) == result
    }

    @Test
    void testPlayerExchange() {
        gameMatrix = buildGameMatrix(2,2,3,4,
                                     1,1,4,3) 
        
        // test
        def result = gameMatrix.playerExchange()

        assert buildGameMatrix(2,2,1,1,
                               4,3,3,4) == result
    }

    @Test
    void testMirror() {
        gameMatrix = buildGameMatrix(2,2,3,4,
                                     1,1,4,3) 
        
        // test
        def result = gameMatrix.mirror()

        assert buildGameMatrix(4,3,1,1,
                               3,4,2,2) == result
    }

    @Test
    void testDupCheck_none() {
        gameMatrix = buildGameMatrix(2,2,3,4,
                                     1,1,4,3) 
        
        // test
        def result = GameMatrix.dupCheck(gameMatrix, canonicals)

        assert [null,null] == result
    }

    @Test
    void testDupCheck_playerExchange() {
        gameMatrix = buildGameMatrix(4,4,3,3,
                                     2,1,1,2) 
        def gmMatch = gameMatrix.playerExchange()
        canonicals[gmMatch] = gmMatch
 
        // test
        def result = GameMatrix.dupCheck(gameMatrix, canonicals)

        assert [gmMatch, "player"] == result
    }

    @Test
    void testDupCheck_playerExchangeRowExchange() {
        gameMatrix = buildGameMatrix(2,2,3,4,
                                     1,1,4,3) 
        def gmMatch = gameMatrix.playerExchange().rowExchange()
        canonicals[gmMatch] = gmMatch
 
        // test
        def result = GameMatrix.dupCheck(gameMatrix, canonicals)

        assert [gmMatch, "row ${dot} player"] == result
    }

    @Test
    void testDupCheck_playerExchangeColExchange() {
        gameMatrix = buildGameMatrix(4,1,3,3,
                                     2,4,1,2) 
        def gmMatch = gameMatrix.playerExchange().colExchange()
        canonicals[gmMatch] = gmMatch
 
        // test
        def result = GameMatrix.dupCheck(gameMatrix, canonicals)

        assert [gmMatch, "column ${dot} player"] == result
    }

    @Test
    void testDupCheck_playerExchangeMirror() {
        gameMatrix = buildGameMatrix(4,2,3,3,
                                     2,1,1,4) 
        def gmMatch = gameMatrix.playerExchange().mirror()
        canonicals[gmMatch] = gmMatch
 
        // test
        def result = GameMatrix.dupCheck(gameMatrix, canonicals)

        assert [gmMatch, "mirror ${dot} player"] == result
    }
}
