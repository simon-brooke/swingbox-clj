# swingbox-clj

A Clojure library designed to allow opening HTML windows from Clojure.

## Rationale

It's pretty hard to visualise data or create desktop UI components for Clojure applications. This is part of an exploration to see whether an HTML component can help.

There is a supposedly open source Java wrapper for chromium,  [java-cef](https://bitbucket.org/chromiumembedded/java-cef/src/master/), but it doesn't build (for me, following [the instructions](https://bitbucket.org/chromiumembedded/java-cef/wiki/BranchesAndBuilding.md), although it does appear to be under current development); There's another that's commercial, [jxbrowser](https://www.teamdev.com/jxbrowser) but it isn't useful when trying to develop open source components. [SwingBox](https://mvnrepository.com/artifact/net.sf.cssbox/swingbox/1.0) is pure Java and open source so I thought it worth playing with.

This is simple and within limitations works. The browser isn't live (at least not at this stage), clicking links does not work and does not reload pages. Also, JavaScript is not interpreted.

## Usage

```clojure
(show-in-window "From a string" "Hello")

(show-in-window "From the tinkerweb" "https://www.journeyman.cc/~simon/")

(show-in-window "From Hiccup" [:html [:head [:title "Test from Hiccup"]][:body [:h1 "Excuse me"] [:p "This is hiccup"]]])

(set-visible (set-content-markdown (create-window "From a Markdown file") "README.md"))
```

## License

Copyright © 2019 FIXME

Distributed under the [GNU Lesser General Public Licence 3.0](https://www.gnu.org/licenses/lgpl-3.0.en.html) or (at
your option) any later version, following the license of SwingBox.
