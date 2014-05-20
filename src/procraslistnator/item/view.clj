(ns procraslistnator.item.view
  (:require [hiccup.page :refer [html5]]
            [hiccup.core :refer [html h]]))

(defn items-page [items]
  (html5 {:lang :en}
         [:head
          [:title "ProcrasLISTnator"]
          [:meta {:name :viewport
                  :content "width=device-width, initial-scale=1.0"}]
          [:link {:href "/bootstrap-3.1.1/css/bootstrap.min.css"
                  :rel :stylesheet}]]
         [:body
          [:div.container]
          [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"}]
          [:script {:src "bootstrap-3.1.1/css/bootstrap.min.css"}]]))
