(defproject procraslistnator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.2.2"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.3.3"]
                 [postgresql/postgresql "9.1-901.jdbc4"]]

  :min-lein-version "2.0.0"

  :uberjar-name "procraslistnator.jar"

  :main procraslistnator.core

  :profiles {:dev
             {:main procraslistnator.core/-dev-main}})
