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

(defn decode-image! [input-file output-file]
  (let [orig-image   (-> input-file io/input-stream ImageIO/read)
        width        (.getWidth orig-image)
        height       (.getHeight orig-image)
        output-image (new BufferedImage width height BufferedImage/TYPE_INT_RGB)]
    (doseq [row-index    (range height)
            column-index (range width)
            :let         [rgb-object (new Color (.getRGB orig-image column-index row-index))
                          pixel (vector (.getRed rgb-object)
                                        (.getGreen rgb-object)
                                        (.getBlue rgb-object))
                          [r g b] (convert-pixel pixel)]]
      (.setRGB output-image column-index row-index (.getRGB (new Color r g b))))
    (ImageIO/write
     output-image
     "jpg"
     (new File output-file))))
