(ns procraslistnator.core
  (:require [procraslistnator.item.model :as items])
  (:require [ring.adapter.jetty :as ring]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes ANY GET POST PUT DELETE]]
            [compojure.route :refer [not-found]]))

(def db "jdbc:postgresql://localhost/procraslistnator")

(defroutes routes
  (GET "/" [] "<h2>Home Page</h2>")
  (not-found "Page not found."))

(defn -main [port]
  (items/create-table db)
  (ring/run-jetty routes {:port (Integer. port)}))

(defn -dev-main [port]
  (items/create-table db)
  (ring/run-jetty (wrap-reload #'routes) {:port (Integer. port)}))
