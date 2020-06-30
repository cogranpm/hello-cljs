(ns hello-world.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   ;;[react-dom]
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [clojure.set :as set]
   [clojure.string :as string]
   [cljs.reader :as reader]
   [goog.dom :as dom]
   [goog.events :as events]
   [goog.events.EventType :as event-type]
   [cljs.core.async :refer [put! chan <!]])

  ;; these are java imports
  ;; OR according to 101 its so we can use short names for google closure constructors
  (:import [goog.net Jsonp]
           [goog Uri]
           [goog.string Const]
           [goog.html TrustedResourceUrl]
           )
  )


;; -- Domino 1 - Event Dispatch -----------------------------------------------

(defn dispatch-timer-event
  []
  (let [now (js/Date.)]
    (rf/dispatch [:timer now])))  ;; <-- dispatch used

;; Call the dispatching function every second.
;; `defonce` is like `def` but it ensures only one instance is ever
;; created in the face of figwheel hot-reloading of this file.
(defonce do-timer (js/setInterval dispatch-timer-event 1000))

;; re-frame example
;; -- Domino 2 - Event Handlers -----------------------------------------------

(rf/reg-event-db              ;; sets up initial application state
 :initialize                 ;; usage:  (dispatch [:initialize])
 (fn [_ _]                   ;; the two parameters are not important here, so use _
   {:time (js/Date.)         ;; What it returns becomes the new application state
    :time-color "#f88"}))    ;; so the application state will initially be a map with two keys

(rf/reg-event-db                ;; usage:  (dispatch [:time-color-change 34562])
 :time-color-change            ;; dispatched when the user enters a new colour into the UI text field
 (fn [db [_ new-color-value]]  ;; -db event handlers given 2 parameters:  current application state and event (a vector)
   (assoc db :time-color new-color-value)))   ;; compute and return the new application state


(rf/reg-event-db                 ;; usage:  (dispatch [:timer a-js-Date])
 :timer                         ;; every second an event of this kind will be dispatched
 (fn [db [_ new-time]]          ;; note how the 2nd parameter is destructured to obtain the data value
   (assoc db :time new-time)))  ;; compute and return the new application state


;; -- Domino 4 - Query  -------------------------------------------------------

(rf/reg-sub
 :time
 (fn [db _]     ;; db is current app state. 2nd unused param is query vector
   (:time db))) ;; return a query computation over the application state

(rf/reg-sub
 :time-color
 (fn [db _]
   (:time-color db)))

;; -- Domino 5 - View Functions ----------------------------------------------

(defn clock
  []
  [:div.example-clock
   {:style {:color @(rf/subscribe [:time-color])}}
   (-> @(rf/subscribe [:time])
       .toTimeString
       (string/split " ")
       first)])

(defn color-input
  []
  [:div.color-input
   "Time color: "
   [:input {:type "text"
            :value @(rf/subscribe [:time-color])
            :on-change #(rf/dispatch [:time-color-change (-> % .-target .-value)])}]])  ;; <---


(defn ui
  []
  [:div
   [:h1 "Hello world, it is now"]
   [clock]
   [color-input]])




;; -- Entry Point -------------------------------------------------------------

(defn render
  []
  (reagent.dom/render [ui]
                      (js/document.getElementById "app")))

(defn ^:dev/after-load clear-cache-and-render!
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the Reframe subscription cache.
  (rf/clear-subscription-cache!)
  (render))

(defn run
  []
  (rf/dispatch-sync [:initialize]) ;; put a value into application state
  (render)                         ;; mount the application's ui into '<div id="app" />'
  )

;;____________------------______________
;; end of reframe stuff


(defn what-kind? []
  "cruel no more with a live EDIT")

(defn hello [])

;; clojurescript functional web article example
;; -------------------------------------------
(def healthy
  #{"lettuce" "fish" "apples" "carrots"})

(def tasty
  #{"apples" "cake" "candy" "fish"})

(defn talk []
  (js/alert
   (str
    "Some Healthy and tasty foods are:"
    (string/join ". " (set/intersection healthy tasty)))))


(defn attrs-props [attrs]
  )

(defn closing-tag [tag attrs]
  )

(defn wrapping-tag [tag attrs inner]
  )

(defn compile-form [form]
  )

 

;; google style event handling
;; (defn handle-click [event]
;;   (talk))

;; (events/listen
;;  (dom/getElement "btn")
;;  goog.events.EventType.CLICK
;;  handle-click)

;; (js/console.log (what-kind?))




;;react rendering
;;(.render js/ReactDOM
;;         (.createElement js/React "h2" nil "Hello React!")
;;         (.getElementById js/document "app"))


;; javascript browser style event handling
;; with all associated browser quirks
;; (defn click-event[]
;;   (println "button clicked")
;;   (talk))


;; (defn load-event []
;;   (println "page loaded")
;;   (.addEventListener (.getElementById js/document "btn") "click" click-event false)
;;   )

;; (.addEventListener js/window "load", load-event false)
;;(println "Hello World!")


;; nolan clojurescript 101 example
;; (defn listen [el type]
;;   (let [out (chan)]
;;     (events/listen el type
;;                    (fn [e] (put! out e)))
;;     out))

;; (let [clicks (listen (dom/getElement "search") "click")]
;;   (go (while true
;;         (.log js/console (<! clicks)))))


;; (def wiki-search-url
;;   "http://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=")

;; (defn jsonp-fn [uri]
;;   (let [out (chan)
;;         req (Jsonp. (Uri. uri))]
;;     (.send req nil (fn [res] (put! out res)))
;;     out))

;; (defn query-url [q]
;;   (str wiki-search-url q))

;; (defn user-query []
;;   (.-value (dom/getElement "query")))

;; (defn init []
;;   (let [clicks (listen (dom/getElement "search") "click")]
;;     (go (while true
;;           (<! clicks)
;;           (.log js/console (<! (jsonp-fn (query-url (user-query)))))))))



;; ;;(.log js/console (dom/getElement "query"))
;; ;;(go (.log js/console (<! (jsonp-fn (query-url "cats")))))
;; (init)


