(ns hello-world.views
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [clojure.string :as string]
   ))


;; -- Domino 5 - View Functions ----------------------------------------------
;; different views depending on which part of the app state has changed
;; you "subscribe" to the the bits of state that you want
;; all these view functions make up the ui of the page
;; hiccup syntax for building the nodes

(defn clock
  []
  [:div.example-clock
   {:style {:color @(rf/subscribe [:time-color])}}
   (-> @(rf/subscribe [:time])
       .toTimeString
       (string/split " ")
       first)])


;; this thing triggers an event handler by calling rf/dispatch
;; arg is vector, element 1 is event type, the rest is optional data
;; rf/subscribe is used to look up app state
(defn color-input
  []
  [:div.color-input
   "Time color: "
   [:input {:type "text"
            :value @(rf/subscribe [:time-color])
            :on-change #(rf/dispatch [:time-color-change (-> % .-target .-value)])}]]) 


(defn ui
  []
  [:div
   [:h1 "Hello world, it is now"]
   [clock]
   [color-input]])

