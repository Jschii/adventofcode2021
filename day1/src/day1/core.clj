(ns day1.core
  (:require [clojure.string :refer [split-lines]]
            [clojure.edn :refer [read-string]]))

(defn -main [& args]
  (let [input (->> "input.txt"
                   slurp
                   split-lines
                   (map read-string))
        silver (->> input
                    (partition 2 1)
                    (filter (fn [[f s]] (> s f)))
                    count)
        gold (->> input
                  (partition 3 1)
                  (map (partial reduce +))
                  (partition 2 1)
                  (filter (fn [[f s]] (> s f)))
                  count)]
    (prn silver)
    (prn gold)))
