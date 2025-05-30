(ns conversor.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clj-http.client :as http-client])
  (:gen-class))

(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "eur"]
   ["-p" "--para moeda destino" "moeda a ual queremos saber o valor"]])

(def chave "e65d183399d04718b8dda86aa0a25a05")

(def api-url "https://api.currencyfreaks.com/v2.0/rates/latest")

(defn parametrizar-moedas [de para]
  (str de "_" para))

(defn -main                                                 ; static function
  [& args]
  (let [{:keys [de para]} (:options
                            (parse-opts args opcoes-do-programa))]
        (prn "Cotação:" (http-client/get api-url
                                        {:query-params {"q"      (parametrizar-moedas de para)
                                                        "apikey" chave}}))))
