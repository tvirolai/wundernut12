(ns wundernut12.core
  (:require [clojure.string :as s]
            [wundernut12.image-decode :as decode]
            [wundernut12.ocr :as ocr]))

(def input-file
  "https://github.com/wunderdogsw/wundernut-vol12/raw/main/parchment.png")

(def output-file
  "resources/output.jpg")

(defn rot-5-decode [text]
  (let [alphabet (seq "ABCDEFGHIJKLMNOPQRSTUVWXYZ")]
    (->> text
         (map (fn [c]
                (let [i (.indexOf alphabet c)]
                  (if (= -1 i)
                    c
                    (nth (cycle alphabet) (+ 5 i))))))
         (apply str))))

(defn -main []
  (println "Decoding the input image to black & white...")
  (decode/decode-image! input-file output-file)
  (println "Performing OCR on the generated image...")
  (let [res (ocr/ocr-image! output-file)
        decoded (rot-5-decode res)]
    (println "Decoded result:")
    (doseq [line (s/split-lines decoded)]
      (println line))))
