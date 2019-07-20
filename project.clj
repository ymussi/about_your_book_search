(defproject about_your_book_search "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [metosin/compojure-api "1.1.11"]
                 [http-kit "2.4.0-alpha4"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/data.zip "0.1.1"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-oauth "1.5.5"]
                 [clj-http "2.2.0"]
                 [clj-oauth "1.5.2"]]
  :main about-your-book-search.api
  :uberjar-name "about-books-standalone.jar"
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.12.5"]]}
             :uberjar {:aot :all}})
