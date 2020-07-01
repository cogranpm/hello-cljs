(ns hello-world.db
  (:require
   [re-frame.core :as rf]
   [cljs.reader :as reader]
   [cljs.spec.alpha :as s]
   ))

;; this would have the "spec" for the app state "database"
;; also called a schema for the in memory database
