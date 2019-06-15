(ns swingbox-clj.core-test
  (:require [clojure.test :refer :all]
            [swingbox-clj.core :refer :all]))

(deftest create-window-test
  (testing "Creating a window"
    (let [expected true
          actual (window? (create-window))] ;; no title
      (is (= actual expected)))
    (let [expected true
          actual (window? (create-window "With title"))]
      (is (= actual expected)))
    ))

(deftest is-window-test
  (testing "window?"
    (let [expected false
          actual (window? {:foo :bar})]
      (is (= actual expected) "Wrong keys"))
    (let [expected false
          actual (window? nil)]
      (is (= actual expected) "Not a map at all"))
    (let [expected false
          actual (window? {:frame 7 :browser "Bingo"})]
      (is (= actual expected) "Wrong values for right keys"))
    (let [expected true
          actual (window? (create-window))]
      (is (= actual expected) "Right keys"))
    (let [expected true
          actual (window? (assoc (create-window) :foo "bar"))]
      (is (= actual expected) "Additional keys are tolerated"))))

