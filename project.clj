(defproject wundernut12 "0.1.0-SNAPSHOT"
  :description "A solution for the Wundernut puzzle vol 12"
  :url "https://github.com/tvirolai/wundernut12"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/data.json "2.4.0"]
                 [clj-http "3.12.3"]]
  :repl-options {:init-ns wundernut12.core}
  :main wundernut12.core/-main)
