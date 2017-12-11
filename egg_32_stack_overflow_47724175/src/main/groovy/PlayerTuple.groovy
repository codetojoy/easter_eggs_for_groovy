
// Payoff Tuple (a,b) found in game matrix position.
// The Tuple is immutable, if we need to change it, we create a new one.
// "equals()" checks for equality against another Tuple instance.
// "hashCode()" is needed for insertion/retrievel of a Tuple instance into/from
// a "Map" (in this case, the hashCode() actually a one-to-one mapping to the integers.)

class PlayerTuple {

   final int a,b

   PlayerTuple(int a,int b) {
      assert 1 <= a && a <= 4
      assert 1 <= b && b <= 4
      this.a = a
      this.b = b
   }

   boolean equals(def o) {
      if (!(o && o instanceof PlayerTuple)) {
         return false
      }
      return a == o.a && b == o.b
   }

   int hashCode() {
      return (a-1) * 4 + (b-1)
   }

   String toString() {
      return "($a,$b)"
   }

   PlayerTuple flip() {
      return new PlayerTuple(b,a)
   }
}

