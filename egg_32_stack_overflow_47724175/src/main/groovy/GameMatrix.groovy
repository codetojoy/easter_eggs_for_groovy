
// "GameMatrix" is an immutable structure of 2 x 2 PlayerTuple:
// top left, top right, bottom left, bottom right
// "equals()" checks for equality against another GameMatrix instance.
// "hashCode()" is needed for insertion/retrievel of a GameMatrix instance into/from
// a "Map" (in this case, the hashCode() actually a one-to-one mapping to the integers)

class GameMatrix {

    final PlayerTuple tl, tr, bl, br

    GameMatrix(PlayerTuple tl,tr,bl,br) {
        assert tl && tr && bl && br
        this.tl = tl; this.tr = tr
        this.bl = bl; this.br = br
    }

    GameMatrix colExchange() {
        return new GameMatrix(tr,tl,br,bl)
    }

    GameMatrix rowExchange() {
        return new GameMatrix(bl,br,tl,tr)
    }

    GameMatrix playerExchange() {
        return new GameMatrix(tl.flip(),bl.flip(),
                              tr.flip(),br.flip())
    }

    GameMatrix mirror() {
      // columnEchange followed by rowExchange
      return new GameMatrix(br,bl,tr,tl)
    }

    String toString() {
      return "[ ${tl},${tr} | ${bl},${br} ]"
    }

    boolean equals(def o) {
      if (!(o && o instanceof GameMatrix)) {
         return false
      }
      return tl == o.tl && tr == o.tr && bl == o.bl && br == o.br
    }

    int hashCode() {
      return (( tl.hashCode() * 16 + tr.hashCode() ) * 16 + bl.hashCode() ) * 16 + br.hashCode()     
    }

    // Check whether a GameMatrix can be mapped to a member of the "canonicals", the set of 
    // equivalence class representatives, using a reduced set of transformations. Technically,
    // "canonicals" is a "Map" because we want to not only ask the membership question, but 
    // also obtain the canonical member, which is easily done using a Map. 
    // The method returns the array [ canonical member, string describing the operation chain ]
    // if found, [ null, null ] otherwise.

    static dupCheck(GameMatrix gm, Map canonicals) {
       // Applying only one of rowExchange, colExchange, mirror will
       // never generate a member of "canonicals" as all of these have player A payoff 4
       // at topleft, and so does gm
       def q = gm.playerExchange()

       def chain = "player"
       if (q.tl.a == 4) {
            // no-op
       } else if (q.tr.a == 4) {
            q = q.colExchange(); chain = "column ∘ ${chain}"
       } else if (q.bl.a == 4) {
            q = q.rowExchange(); chain = "row ∘ ${chain}"
       } else if (q.br.a == 4) {
            q = q.mirror(); chain = "mirror ∘ ${chain}"
       } else {
          assert false : "Can't happen"
       }
       assert q.tl.a == 4
       return (canonicals[q]) ? [ canonicals[q], chain ] : [ null, null ]
    }

    // Main enumerates all the possible Game Matrixes and builds the
    // set of equivalence class representatives, "canonicals".
    // We only bother to generate Game Matrixes of the form
    // [ (4,_) , (_,_) | (_,_) , (_,_) ]
    // as any other Game Matrix can be trivially transformed into the
    // above form using row, column and player exchange.

    public static void main(String[] argv) {
       def canonicals = [:]
       def i = 1
       [3,2,1].permutations { payoffs_playerA ->
          [4,3,2,1].permutations { payoffs_playerB ->
             def gm = new GameMatrix(
                           new PlayerTuple(4,                  payoffs_playerB[0]),
                           new PlayerTuple(payoffs_playerA[0], payoffs_playerB[1]),
                           new PlayerTuple(payoffs_playerA[1], payoffs_playerB[2]),
                           new PlayerTuple(payoffs_playerA[2], payoffs_playerB[3])
                      )
             def q = gm.playerExchange()

             def ( c, chain ) = dupCheck(gm,canonicals)
             if (c) {
                System.out << "${gm} equivalent to ${c} via ${chain}\n"
             }
             else {
                System.out << "${gm} accepted as canonical entry ${i}\n"
                canonicals[gm] = gm
                i++
             }
          }
       }
    } // main
} // class
