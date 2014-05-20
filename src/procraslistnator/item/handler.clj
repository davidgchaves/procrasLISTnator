(ns procraslistnator.item.handler
  (:require [procraslistnator.item.model :refer [create-item
                                                 read-items
                                                 update-item
                                                 delete-item]])
  (:require [procraslistnator.item.view :refer [items-page]]))

(defn handle-index-items [req]
  (let [db (:procraslistnator/db req)
        items (read-items db)]
    {:status 200
     :headers {}
     :body (items-page items)}))

(defn handle-create-item [req]
  (let [name (get-in req [:params "name"])
        description (get-in req [:params "description"])
        db (:procraslistnator/db req)
        item-id (create-item db name description)]
    {:status 302
     :headers {"Location" "/items"}
     :body ""}))

(defn handle-delete-item [req]
  (let [db (:procraslistnator/db req)
        item-id (java.util.UUID/fromString (:item-id (:route-params req)))
        exists? (delete-item db item-id)]
    (if exists?
      {:status 302
       :headers {"Location" "/items"}
       :body ""}
      {:status 404
       :headers {}
       :body "The item doesn't exist."})))

(defn handle-update-item [req]
  (let [db (:procraslistnator/db req)
        item-id (java.util.UUID/fromString (:item-id (:route-params req)))
        checked (get-in req [:params "checked"])
        exists? (update-item db item-id (= "true" checked))]
    (if exists?
      {:status 302
       :headers {"Location" "/items"}
       :body ""}
      {:status 404
       :headers {}
       :body "The item doesn't exist."})))
