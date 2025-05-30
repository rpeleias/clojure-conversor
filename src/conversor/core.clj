(ns conversor.core
  (:require [clojure.tools.cli :refer [parse-opts]]
             [cheshire.core :refer [parse-string]]
             [clj-http.client :as http-client])
  (:gen-class))

(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "eur"]
   ["-p" "--para moeda destino" "moeda a ual queremos saber o valor"]])

(def chave "e65d183399d04718b8dda86aa0a25a05")

(def api-url "https://api.currencyfreaks.com/v2.0/rates/latest")

(defn parametrizar-moedas [de para]
  (str de "_" para))

(defn obter-cotacao [de para]
  (let [moedas (parametrizar-moedas de para)]
    (-> (:body (http-client/get api-url
                                {:query-params { "q" moedas
                                                "apikey" chave}}))
        (parse-string)
        (get-in ["rates" para]))))

(defn- formatar [cotacao de para]
  (str "1 " de " equivale a " cotacao " em " para))

(defn -main                                                 ; static function
  [& args]
  (let [{:keys [de para]} (:options
                            (parse-opts args opcoes-do-programa))]
    (-> (obter-cotacao de para)
        (formatar de para)
        (prn))))
