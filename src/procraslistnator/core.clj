(ns procraslistnator.core
  (:require [ring.adapter.jetty :as ring]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defroutes routes
  (GET "/" [] "<h2>Home Page</h2>")
  (not-found "Page not found."))

(defn -main [port]
  (ring/run-jetty routes {:port (Integer. port)}))

(defn -dev-main [port]
  (ring/run-jetty (wrap-reload #'routes) {:port (Integer. port)}))
