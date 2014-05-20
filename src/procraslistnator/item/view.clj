(ns procraslistnator.item.view
  (:require [hiccup.page :refer [html5]]
            [hiccup.core :refer [html h]]))

(defn new-item []
  (html
    [:form.form-horizontal
     {:method "POST" :action "/items"}

     [:div.form-group
      [:label.control-label.col-sm-2 {:for :name-input}
       "Name"]
      [:div.col-sm-10
       [:input#name-input.form-control
        {:name :name
         :placeholder "Name"}]]]

     [:div.form-group
      [:label.control-label.col-sm-2 {:for :desc-input}
       "Description"]
      [:div.col-sm-10
       [:input#desc-input.form-control
        {:name :description
         :placeholder "Description"}]]]

     [:div.form-group
      [:div.col-sm-offset-2.col-sm-10
       [:input.btn.btn-primary
        {:type :submit
         :value "New item"}]]]]))

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
              [:div.col-sm-offset-1 "There are no items."])]

           [:div.col-sm-6
            [:h2 "Create a new item"]
            (new-item)]]

          [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"}]
          [:script {:src "bootstrap-3.1.1/css/bootstrap.min.css"}]]))