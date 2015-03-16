(ns perforate-x.core
  (:require
   [cljs.nodejs :as nodejs]))

(def Benchmark
  (nodejs/require "benchmark"))

(def goals (atom {}))

(defn defgoal
  [id & _]
  (swap! goals assoc id
         (.. (.Suite Benchmark)
             (on "cycle" (fn [e] (.log js/console (js/String (.-target e)))))
             (on "complete"
                 (fn [e]
                   (this-as
                    *this*
                    (let [fastest (-> *this*
                                      (.filter "fastest")
                                      (.pluck "name")
                                      (aget 0))]
                      (.log js/console "Fastest is" fastest))))))))

(defn defcase
  [goal id _ f]
  (swap! goals update-in [goal] #(.add % (name id) f)))

(defn run-goals
  []
  (doseq [[id goal] @goals]
    (.log js/console "Goal:" (name id))
    (.run goal))
  (.exit js/process))
