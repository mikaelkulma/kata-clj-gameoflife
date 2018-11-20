(ns gol.core-test
  (:require [midje.sweet :refer :all]
            [gol.core :refer :all]))

  (facts "about `game of life`"
       (fact "a seed with a single cell evolves into 0 cells in the 1st generation "
             (tick #{[1 1]}) => #{})
       (fact "a blinker blinks"
             (nth (iterate tick #{[1 1] [2 1] [3 1]}) 10) => #{[1 1] [2 1] [3 1]})
       (fact "a cross shaped seed evolves into 12 cells in the 3rd generation"
             (count (nth (iterate tick #{[2 1] [1 2] [2 2] [3 2] [2 3]}) 3)) => 12)
       (fact "a die-hard seed evolves into 0 cells in the 131th generation"
             (count (nth (iterate tick #{[1 2] [2 2] [2 3] [7 1] [6 3] [7 3] [8 3]}) 131)) => 0 )
       (fact "a R-pentomino seed evolves into 116 cells in the 1103th generation"
             (count (nth (iterate tick #{[1 2] [2 2] [2 1] [2 3] [3 1]}) 1103)) => 116)
       (fact "a acorn seed evolves into 633 cells in the 5206th generation"
             (count (nth (iterate tick #{[1 3] [2 3] [2 1] [4 2] [5 3] [6 3] [7 3]}) 5206)) => 633))


