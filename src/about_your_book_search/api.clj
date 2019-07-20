(ns about-your-book-search.api
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [org.httpkit.server :as httpkit]
            [about-your-book-search.core :as find]
            [schema.core :as s]
            )
  (:gen-class))

(s/defschema Book
  {:bookName s/Str})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "About Your Book Search"
                    :description "Find details about your book."}
             :tags [{:name "api", :description ""}]}}}

    (context "/api" []
      :tags ["Book Details"]

      (POST "/search-your-book" []
           ;:return Book
           :summary "Find details about your book name."
           :body [bookName Book]
           (ok (find/search-book (:bookName bookName))))
      )))

(defn -main []
  (httpkit/run-server #'app {:port 3000})
  (println "Server started on: localhost:3000"))
