(ns hello-world.subs
  (:require
   [re-frame.core :as rf]
   ))


;; -- Domino 4 - Query  -------------------------------------------------------
;; these provide data whenever you see a subscribe call from elsewhere
;; might be in the views

(rf/reg-sub
 :time
 (fn [db _]     ;; db is current app state. 2nd unused param is query vector
   (:time db))) ;; return a query computation over the application state

(rf/reg-sub
 :time-color
 (fn [db _]
   (:time-color db)))

