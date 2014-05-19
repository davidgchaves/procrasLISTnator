(ns procraslistnator.core
  (:require [procraslistnator.item.model :as items])
  (:require [ring.adapter.jetty :as ring]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.handler.dump :refer [handle-dump]]
            [compojure.core :refer [defroutes ANY GET POST PUT DELETE]]
            [compojure.route :refer [not-found]]))

(def db "jdbc:postgresql://localhost/procraslistnator")

(defroutes routes
  (GET "/" [] "<h2>Home Page</h2>")
  (GET "/pretty-request" [] handle-dump)
  (not-found "Page not found."))

(def app
  routes)

(defn -main [port]
  (items/create-table db)
  (ring/run-jetty app {:port (Integer. port)}))

(defn -dev-main [port]
  (items/create-table db)
  (ring/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
