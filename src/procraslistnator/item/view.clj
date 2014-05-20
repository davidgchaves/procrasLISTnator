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
          [:div.container
           [:h1 "My Items"]
           [:div.row
            (if (seq items)
              [:table.table.table-striped
               [:thead
                [:tr
                 [:th "Name"]
                 [:th "Description"]]]
               [:tbody
                (for [i items]
                  [:tr
                   [:th (h (:name i))]
                   [:th (h (:description i))]])]]
              [:div.col-sm-offset-1 "There are no items."])]]
          [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"}]
          [:script {:src "bootstrap-3.1.1/css/bootstrap.min.css"}]]))
