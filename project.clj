(defproject swingbox-clj "0.1.0-SNAPSHOT"
  :description "A Clojure library designed to allow opening HTML windows from Clojure."
  :url "http://example.com/FIXME"
  :license {:name "GNU Lesser General Public Licence 3.0"
            :url "https://www.gnu.org/licenses/lgpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]
                 [markdown-clj "0.9.99" :exclusions [com.keminglabs/cljx]]
                 [net.sf.cssbox/swingbox "1.0"]])
