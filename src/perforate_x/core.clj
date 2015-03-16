(ns perforate-x.core
  (:require
   [perforate.core :as perf]))

(defmacro defgoal
  [goal doc]
  `(perf/defgoal ~(symbol (name goal)) ~doc))

(defmacro defcase
  [goal id args form]
  (let [form (last `(list ~@form))]
    `(perf/defcase ~(symbol (name goal)) ~id ~args ~form)))
