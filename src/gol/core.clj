(ns gol.core
  (:require [clojure.set :as set]))

(defn neighbours
  [c] (let [[x y] c]
        #{[(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]
         [(dec x)  y] [(inc x) y]
         [(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
         }))

(defn alive-neighbours
  [c b] (set/intersection (neighbours c) b))

(defn dead-neighbours
  [c b] (set/difference (neighbours c) b))

(defn neighbour-count
  [c b] (count(alive-neighbours c b )))

(defn survives
  [c b] (and (< 1 ( neighbour-count c b)) (> 4 ( neighbour-count c b))))

(defn evolve
  [c b] (if (survives c b) c nil))

(defn resurrects
  [c b] (= 3 (neighbour-count c b)))

(defn resurrect
  [c b] (if (resurrects c b) c nil))

(defn resurrecting-deadcells
  [c b](filter (fn [dc] (resurrect dc b)) (dead-neighbours c b)))

(defn surviving-livecells
  [b] (filter (fn [c] ( evolve c b)) b))

(defn tick
  [b] (set/union
        (set (surviving-livecells b))
        (set (apply concat (map #(resurrecting-deadcells % b) b)))))