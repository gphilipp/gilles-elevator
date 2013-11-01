(defproject gilles-elevator "0.1.0-SNAPSHOT"
  :description "My solution to Code Story elevator S03E01"
  :url "http://philippart.me/codestory-elevator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [compojure "1.1.5"]]
  :dev-dependencies [[lein-ring "0.8.7"]]
  :plugins [[lein-cloudbees "1.0.4"]]
  :cloudbees-app-id "gphilipp/gilles-elevator")
