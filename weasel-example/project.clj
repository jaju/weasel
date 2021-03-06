(defproject weasel-example "NOT_RELEASED"
  :description "example project for the weasel ClojureScript REPL env"
  :url "https://github.com/tomjakubowski/weasel"
  :license {:name "Unlicense"
            :url "http://unlicense.org/UNLICENSE"
            :distribution :repo}

  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [weasel "0.7.0"]]

  :repl-options {:welcome (println "Run (start-weasel) to start a Weasel REPL.")
                 :init (do
                         (require 'weasel.repl.websocket)
                         (defn start-weasel [& opts]
                           (cemerick.piggieback/cljs-repl
                             (apply weasel.repl.websocket/repl-env opts))))}
  :source-paths ["src"]
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]]
                   :plugins [[lein-cljsbuild "1.0.6"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :cljsbuild {:builds [{:id "weasel-example"
                                         :source-paths ["src"]
                                         :compiler {:output-to "weasel_example.js"
                                                    :output-dir "out"
                                                    :optimizations :none
                                                    :source-map true}}]}}})
