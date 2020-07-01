(defproject hello-clojurescript "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :aliases {"fig" ["run" "-m" "figwheel.main"]}
  :source-paths ["src"]
  :profiles 
  {:dev
      {:dependencies [[org.clojure/clojurescript "1.10.773"]
                      [com.bhauman/figwheel-main "0.2.9"]
                      ;; optional but recommended
                      [com.bhauman/rebel-readline-cljs "0.1.4"]
                      [cljsjs/react-dom "16.2.0-3"]
                      [org.clojure/core.async "1.2.603"]
                      [org.clojure/tools.reader "1.3.2"]
                      [re-frame "1.0.0-rc3"]]
       :resource-paths ["target"]
       ;;:compiler {:asset-path "target/public/cljs-out/dev"}
       :clean-targets ^{:protect false} ["target"]
       }})

