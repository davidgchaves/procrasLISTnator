(ns procraslistnator.core
  (:require [procraslistnator.item.model :as items]
            [procraslistnator.item.handler :refer [handle-index-items
                                                   handle-create-item
                                                   handle-delete-item]])
  (:require [ring.adapter.jetty :as ring]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.handler.dump :refer [handle-dump]]
            [compojure.core :refer [defroutes ANY GET POST PUT DELETE]]
            [compojure.route :refer [not-found]]))

(def db "jdbc:postgresql://localhost/procraslistnator")

(defroutes routes
  (GET "/" [] "<h2>Home Page</h2>")

  (GET "/items" [] handle-index-items)
  (POST "/items" [] handle-create-item)
  (DELETE "/items/:item-id" [] handle-delete-item)

  (GET "/pretty-request" [] handle-dump)
  (not-found "Page not found."))

(defn wrap-db [handler]
  (fn [req]
    (handler (assoc req :procraslistnator/db db))))

(defn wrap-server [handler]
  (fn [req]
    (assoc-in (handler req) [:headers "Server"] "Procraslistnator 101")))

(def app
  (wrap-server
    (wrap-file-info
      (wrap-resource
        (wrap-db
          (wrap-params
            routes))
        "static"))))

(defn -main [port]
  (items/create-table db)
  (ring/run-jetty app {:port (Integer. port)}))

(defn -dev-main [port]
  (items/create-table db)
  (ring/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
