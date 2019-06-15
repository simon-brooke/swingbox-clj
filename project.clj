(defproject swingbox-clj "0.1.0"
  :description "A Clojure library designed to allow opening HTML windows from Clojure."
  :url "http://example.com/FIXME"
  :license {:name "GNU Lesser General Public Licence 3.0"
            :url "https://www.gnu.org/licenses/lgpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]
                 [markdown-clj "0.9.99" :exclusions [com.keminglabs/cljx]]
                 [net.sf.cssbox/swingbox "1.0"]]
  :plugins [[lein-codox "0.10.3"]
            [lein-kibit "0.1.6"]
            [lein-release "1.0.5"]]
;; lein-release not yet working on this.
;;   :release-tasks [["vcs" "assert-committed"]
;;                   ["change" "version" "leiningen.release/bump-version" "release"]
;;                   ["vcs" "commit"]
;;                   ["clean"]
;;                   ["test"]
;;                   ["codox"]
;;                   ["jar"]
;; ;;                  ["deploy" "clojars"]
;;                   ["change" "version" "leiningen.release/bump-version"]
;;                   ["vcs" "commit"]]
  )
