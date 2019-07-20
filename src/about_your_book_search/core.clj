(ns about-your-book-search.core
  (:require [oauth.client :as oauth]
            [clj-http.client :as http]
            [clojure.string :as string]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.data.json :as json]
            [config.config :as cfg])
  (:import [java.net URLEncoder]))

(def main-url "https://www.goodreads.com")
(def url-find-book "/search/index.xml")
(def url-review-title "/book/title.xml")

(def request-token  (format "%s/oauth/request_token/" main-url))
(def authorize  (format "%s/oauth/authorize/" main-url))
(def access-token (format "%s/oauth/access_token/" main-url))

(def default-key {"key" cfg/api-key})

(defn parametric [data]
  "Formats parameters into URLs."
  (string/join "&" (map (fn [[k v]] (str k "=" (. URLEncoder encode v))) data)))

(defn url-get-xml
  ([api-url] (url-get-xml api-url) {})
  ([api-url data]
   (http/get (str main-url api-url "?" (parametric (conj default-key data))))))

(defn parse-xml-body [response]
  "Returns zipped xml response."
  (->> response
       :body
       .getBytes
       java.io.ByteArrayInputStream.
       xml/parse
       zip/xml-zip))

(def url-get-parsed (comp parse-xml-body url-get-xml))

(defn find-book
 [query page field]
   (url-get-parsed url-find-book
                   {"q" query
                    "page" (str page)
                    "field" field}))

(defn reviews-title [title]
  (url-get-parsed url-review-title
                  {"title" title}))

(defn search-book
  [title]
  (def search (reviews-title title))

  (first search))

(defn struct-response
  [title]
  (def map-response (search-book title))

  (get map-response :content))
