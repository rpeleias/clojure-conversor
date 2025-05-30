(ns conversor.formatador
  (:require [cheshire.core :refer [parse-string]]
            [clj-http.client :as http-client]))

(defn formatar [cotacao de para]
  (str "1 " de " equivale a " cotacao " em " para))

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
