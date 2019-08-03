(ns swingbox-clj.core
  (:import java.awt.GraphicsConfiguration
           java.io.File
           java.net.URI
           javax.swing.JFrame
           javax.swing.JScrollPane
           javax.swing.ScrollPaneConstants
           org.fit.cssbox.swingbox.BrowserPane)
  (:require [hiccup.core :refer [html]]
             [markdown.core :as md]
    ))

(defn window?
  "True if `x` is a window within the meaning used in this file, i.e.
  it is a map containing at least the keys `:frame` and `:browser`. such that
  * The value of `:frame` shall be a top-level JFrame.
  * The value of `:browser` shall be a BrowserPane."
  [x]
  (and
    (map? x)
    (instance? javax.swing.JFrame (:frame x))
    (instance? org.fit.cssbox.swingbox.BrowserPane (:browser x))))

(defn create-window
  "Create (but do not show) a top level JFrame window, with this `title` if
  supplied. The created window will contain a browser pane. Returns a map
  containing two keys, `:frame` and `:browser`."
  ([] (create-window nil))
  ([title]
   (let [frame (JFrame. (if title (str title) "Untitled Window"))
         browser (BrowserPane.)
         scroller (JScrollPane.
                    browser
                    javax.swing.ScrollPaneConstants/VERTICAL_SCROLLBAR_AS_NEEDED
                    javax.swing.ScrollPaneConstants/HORIZONTAL_SCROLLBAR_AS_NEEDED)]
     (.setText browser "") ;; window won't open if content is not added to the
                           ;; browser before the browser is added to the frame.
                           ;; you can change the content later.
     (.add frame scroller)
     (.pack frame)
     {:frame frame
      :browser browser})))

;; private JPanel createXPathQueryPanel() {
;;   JPanel p = new JPanel();
;;   p.setLayout(new BorderLayout());
;;   xpathQueryArea.setBorder(BorderFactory.createLineBorder(Color.black));
;;   makeTextComponentUndoable(xpathQueryArea);
;;   JScrollPane scrollPane = new JScrollPane(xpathQueryArea);
;;   scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
;;   scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
;;   final JButton b = createGoButton();
;;   JPanel topPanel = new JPanel();
;;   topPanel.setLayout(new BorderLayout());
;;   topPanel.add(new JLabel("XPath Query (if any):"), BorderLayout.WEST);
;;   topPanel.add(createXPathVersionPanel(), BorderLayout.EAST);
;;   p.add(topPanel, BorderLayout.NORTH);
;;   p.add(scrollPane, BorderLayout.CENTER);
;;   p.add(b, BorderLayout.SOUTH);
;;   return p;
;; }

(defn set-content
  "Set the content of the browser in this `window` to this `content,` which may
  be
  * a URL or string representation of a URL
  * a file (java.io.File)
  * a [hiccup]() structure representing HTML
  * literally anything else.
  Returns the `window`."
  [window content]
  (if
    (window? window)
    (let [browser (:browser window)
          url (try (.toURL (URI/create (str content))) (catch Exception _ nil))
          html (try (html content) (catch Exception _ nil))]
      (cond
        url
        (.setPage browser url)
        html
        (.setText browser html)
        :else
        (case (type content)
          java.lang.String (.setText browser content)
          clojure.lang.PersistentVector (.setText browser (html content))
          java.io.File (.setPage browser (.toURL content))
          (.setText browser (str content))))))
  window)

(defn set-content-markdown
  "Set the content of this `window` to the markdown read from this `filename`.
  Return the `window`."
  [window filename]
  (if
    (window? window)
    (set-content window (md/md-to-html-string (slurp filename))))
  window)

(defn set-visible
  "Set this `window` to be visible if `visible?` is `true` or not supplied;
  else false. Returns the `window`."
  ([window]
   (set-visible window true))
  ([window visible?]
   (if
     (window? window)
     (.setVisible
       (:frame window)
       (true? visible?)))
   window))

(defn show-in-window
  "Show this `content`, in a window with this `title`. Return the window as
  discussed above."
  [title content]
  (set-visible (set-content (create-window title) content)))

