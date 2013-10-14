(ns gilles-elevator.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found]]))

(def commands (atom nil))

(defroutes app
  (GET "/reset" [] (reset! commands (cycle (concat (repeat 6 "UP") (repeat 6 "DOWN")))))
  (GET "/call" [] "called!")
  (GET "/go" [] "")
  (GET "/userHasEntered" [] "")
  (GET "/userHasExited" [] "")
  (GET "/nextCommand" [] (let [action (first @commands)]
                            (swap! commands rest)
                            action))
  (not-found "No page here !"))


(defonce server (jetty/run-jetty #'app {:port 8081 :join? false}))

;(.stop server)
;(.start server)

