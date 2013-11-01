(ns gilles-elevator.omnibus
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found]]))

(def commands (atom nil))

(defn go [direction]
  (take 5 (cycle [[direction "OPEN" "CLOSE"]]))  
)

(defroutes app
  (GET "/reset" [] (do (reset! commands (flatten (cycle [(go "UP") (go "DOWN")])))
                      "Reset eveything" ))
  (GET "/call" [] "")
  (GET "/go" [] "")
  (GET "/userHasEntered" [] "")
  (GET "/userHasExited" [] "")
  (GET "/nextCommand" [] (let [action (first @commands)]
                            (swap! commands rest)
                            action))
  (not-found "No page here !"))

(defonce server (jetty/run-jetty #'app {:port 8081 :join? false}))

(.stop server)
(.start server)


