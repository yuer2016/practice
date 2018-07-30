(ns proc-codec
  (:use
    [gloss core io]
    [gloss.core.formats :only (to-char-buffer)]
    [gloss.core.protocols :only (write-bytes read-bytes)]
    [gloss.data.bytes :only (take-bytes drop-bytes dup-bytes take-contiguous-bytes buf->string)]
    [clojure test walk])
  (:require
    [manifold.stream :as s]))


;; A codec compiled from a simple byte frame
(def byte-codec
  (compile-frame :byte))

(defcodec vector-codec [:byte :byte])

(defcodec map-codec {:first :byte :second :byte})

(def name (encode byte-codec 1 ))


(println name)