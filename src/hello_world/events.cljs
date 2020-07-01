(ns hello-world.events
  (:require
   [hello-world.db]
   [re-frame.core :as rf]
   [cljs.reader :as reader]
   [cljs.spec.alpha :as s]
   ))


;; re-frame example
;; -- Domino 2 - Event Handlers -----------------------------------------------

;; :initialize is triggered from the run method in the core ns

(rf/reg-event-db              ;; sets up initial application state
 :initialize                 ;; usage:  (dispatch [:initialize])
 (fn [_ _]                   ;; the two parameters are not important here, so use _
   {:time (js/Date.)         ;; What it returns becomes the new application state
    :time-color "#f88"}))    ;; so the application state will initially be a map with two keys


;; this is triggered in the view namespace from the on-change of the input element

(rf/reg-event-db                ;; usage:  (dispatch [:time-color-change 34562])
 :time-color-change            ;; dispatched when the user enters a new colour into the UI text field
 (fn [db [_ new-color-value]]  ;; -db event handlers given 2 parameters:  current application state and event (a vector)
   (assoc db :time-color new-color-value)))   ;; compute and return the new application state


;; this is triggered by a timer set off in the core ns
(rf/reg-event-db                 ;; usage:  (dispatch [:timer a-js-Date])
 :timer                         ;; every second an event of this kind will be dispatched
 (fn [db [_ new-time]]          ;; note how the 2nd parameter is destructured to obtain the data value
   (assoc db :time new-time)))  ;; compute and return the new application state


