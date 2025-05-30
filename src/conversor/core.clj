(ns conversor.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "eur"]
   ["-p" "--para moeda destino" "moeda a ual queremos saber o valor"]])

(defn -main                                                 ; static function
  [& args]
  (prn "as opções são:" (:options
                          (parse-opts args opcoes-do-programa))))
