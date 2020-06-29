(ns hello-world.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [react-dom]
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


;; test
(defn average [a b]
  (/ (+ a b) 2.0))


(defn what-kind? []
  "cruel no more")

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
(defn handle-click [event]
  (talk))

(events/listen
 (dom/getElement "btn")
 goog.events.EventType.CLICK
 handle-click)

(js/console.log (what-kind?))




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


