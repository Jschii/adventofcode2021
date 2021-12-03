(ns day2.core
  (:require [clojure.string :refer [split split-lines]]))

(defn- parse [[direction amount-str]]
  (let [amount (Integer/parseInt amount-str)]
    (condp = direction
      "forward" {:f amount :d 0}
      "down" {:f 0 :d amount}
      "up" {:f 0 :d (- amount)})))

(defn- silver [input]
  (reduce (fn [acc cur]
            (merge-with + acc (-> cur (split #" ") parse))) {:f 0 :d 0} input))

(defn- gold [input]
  (reduce (fn [acc cur]
            (let [{current-f :f current-d :d current-aim :a} acc
                  {delta-f :f delta-d :d} (-> cur (split #" ") parse)]
              (cond
                (> delta-f 0) {:f (+ current-f delta-f) :d (+ current-d (* current-aim delta-f)) :a current-aim}
                (not (zero? delta-d)) {:f current-f :d current-d :a (+ current-aim delta-d)}))) {:f 0 :d 0 :a 0} input))

(defn -main [& _]
  (let [input (->> "input.txt"
                   slurp
                   split-lines)
        {silver-f :f silver-d :d} (silver input)
        {gold-f :f gold-d :d} (gold input)]
    (prn (* silver-f silver-d))
    (prn (* gold-f gold-d))))