(ns gilles-elevator.priority
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found]]))

(def app-state (atom {}))

(defn go [direction]
  (take 5 (cycle [[direction "OPEN" "CLOSE"]]))
)

(defn call-at [floor direction]
  (-> (conj (:calls @app-state) {:at floor :to direction})
      swap! app-state [:calls]))

(defroutes app
  (GET "/reset" [] "reset!")
  (GET "/call/atFloor=:floor&to=:to" [floor to] (call-at floor to))
  (GET "/go" [] "")
  (GET "/userHasEntered" [] "")
  (GET "/userHasExited" [] "")
  (GET "/nextCommand" [] "NOTHING")
  (not-found "No page here !"))

(defonce server (jetty/run-jetty #'app {:port 8082 :join? false}))

;(.stop server)
;(.start server)


