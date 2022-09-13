(ns wundernut12.ocr
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.java.io :as io])
  (:import (java.util Base64)))

(def api-endpoint
  "http://api.ocr.space/parse/image")

(def apikey
  (System/getenv "API_KEY"))

(defn file->bytes
  "https://clojuredocs.org/clojure.java.io/input-stream"
  [file]
  (with-open [xin (io/input-stream file)
              xout (java.io.ByteArrayOutputStream.)]
    (io/copy xin xout)
    (.toByteArray xout)))

(defn encode-image [filename]
  (let [bytes (file->bytes filename)
        base64 (.encodeToString (Base64/getEncoder) bytes)]
    (str "data:image/jpeg;base64," base64)))

(defn ocr-image! [file]
  (try
    (let [{:keys [body]} (client/post api-endpoint {:headers     {"Content-Type" "image/jpg"
                                                                  "apikey"       apikey}
                                                    :form-params {:language    "eng"
                                                                  :filetype    "jpg"
                                                                  :base64Image (encode-image file)}})
          parsed-res     (json/read-str body :key-fn keyword)]
      (get-in parsed-res [:ParsedResults 0 :ParsedText]))
    (catch Exception _
      (do
        (println "OCR failed, falling back to pre-OCR'd file.")
        (slurp "resources/text.txt")))))
