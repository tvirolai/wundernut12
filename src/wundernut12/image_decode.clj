(ns wundernut12.image-decode
  (:require [clojure.java.io :as io])
  (:import (java.awt.image BufferedImage)
           (java.io File)
           (java.awt Color)
           (javax.imageio ImageIO)))

(defn- convert-pixel [pixel]
  (condp = pixel
    [252 245 229] [255 255 255] ;; white
    [252 245 230] [0 0 0])) ;; black

(defn- image->pixels [^BufferedImage image]
  (let [width (.getWidth image)]
    (map vec
         (partition width
                    (for [row-index (range (.getHeight image))
                          column-index (range width)
                          :let [rgb (.getRGB image column-index row-index)
                                rgb-object (new Color rgb)]]
                      (vector (.getRed rgb-object)
                              (.getGreen rgb-object)
                              (.getBlue rgb-object)))))))

(defn- convert-image [image]
  (vec (for [row image]
         (mapv convert-pixel row))))

(defn- pixels->file [pixels ^String filename]
  (let [height (count pixels)
        width (count (first pixels))
        output-image (new BufferedImage width height BufferedImage/TYPE_INT_RGB)]
    (doseq [row-index    (range height)
            column-index (range width)
            :let [[^Integer r ^Integer g ^Integer b] (nth (nth pixels row-index) column-index)
                  color (.getRGB (new Color r g b))]]
      (.setRGB output-image column-index row-index color))
    (ImageIO/write
     output-image
     "jpg"
     (new File filename))))

(defn decode-image!
  "Reads the image, converts it to black & white and writes the output to a file."
  [input-file output-file]
  (let [original-image-pixels (-> input-file io/input-stream ImageIO/read image->pixels)
        image-bw (convert-image original-image-pixels)]
    (pixels->file image-bw output-file)))
