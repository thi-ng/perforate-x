# perforate-x

Benchmarking helpers for Clojure & Clojurescript.

This small library provides a unified approach to write benchmarks
using [perforate](https://github.com/davidsantiago/perforate) for
Clojure and the [npm benchmark](https://github.com/RyanMcG/lein-npm)
module for Clojurescript.

Currently not all features of Perforate are supported:

* no goal options
* `defcase` arglists are (still) ignored
* `defcase` expects a thunk for benchmarking (as shown below, though for
  Clojure the fn will be unpacked)
* CLJS version does **not** use macros
  
## Leiningen coordinates

Add this to your `:dev-dependencies` in `project.clj`:

```clj
[perforate-x "0.1.0"]
```

And this your `:plugins`:

```clj
[perforate "0.3.4"]
```

## Usage

```clj
(ns mybench.core
  (:require
   [perforate-x.core :as perf :refer [defgoal defcase]]))

(defgoal :math-goal "dummy benchmark")

(defcase :math-goal :add [] #(+ 1 2))
(defcase :math-goal :mul [] #(* 1 2))
(defcase :math-goal :madd [] #(+ (* 1 2) 3))

;; the following is for CLJS only!
(perf/run-goals)
```

*Note:* By default Perforate expects benchmark namespaces in the
 `benchmarks` subdir of your project...

## Running benchmarks

### Clojure

To run Clojure benchmarks, simply run one of these:

```
lein perforate
lein perforate --quick
lein perforate --quick --csv
```

### Clojurescript

For CLJS, node.js & npm are required and the following needs to be
added to your `project.clj`:

```clj
:perforate {:environments [{:namespaces [mybench.core]}]}

:profiles  {:dev {:plugins [[lein-npm "0.5.0"]
                            [lein-cljsbuild "1.0.5"]]
                  :node-dependencies [[benchmark "1.0.0"]]}}

:cljsbuild {:builds [{:id "bench"
                      :source-paths ["src" "benchmarks"]
                      :notify-command ["node" "target/cljs/benchmark.js"]
                      :compiler {:target :nodejs
                                 :output-to "target/cljs/benchmark.js"
                                 :optimizations :simple
                                 :pretty-print true}}]}
```

Prior to the first benchmark run, invoke `lein deps` to download npm
deps, then launch benchmark via:

```
lein do clean, cljsbuild once bench
```

## License

Copyright © 2015 Karsten Schmidt

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
