(defproject perforate-x "0.1.0"
  :description  "Benchmarking helpers for Clojure & Clojurescript"
  :url          "https://github.com/postspectacular/perforate-x"
  
  :license      {:name "Eclipse Public License"
                 :url "http://www.eclipse.org/legal/epl-v10.html"}

  :scm          {:name "git"
                 :url  "https://github.com/postspectacular/perforate-x"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3117"]
                 [perforate "0.3.4"]]

  :profiles     {:dev {:plugins [[lein-cljsbuild "1.0.5"]
                                 [lein-npm "0.5.0"]]
                       :node-dependencies [[benchmark "1.0.0"]]}}

  :cljsbuild    {:builds [{:id "simple"
                           :source-paths ["src"]
                           :compiler {:output-to "target/perforate-x.js"
                                      :optimizations :whitespace
                                      :pretty-print true}}]}

  :pom-addition [:developers [:developer
                              [:name "Karsten Schmidt"]
                              [:url "http://postspectacular.com"]
                              [:timezone "0"]]])
