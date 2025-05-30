(ns conversor.core
  (:gen-class))

(defn- valores-em [argumento]
  (cond
    (.startsWith argumento "--de=")
    {:de (.substring argumento 5)}
    (.startsWith argumento "--para=")
    {:para (.substring argumento 7)}
    :else {}))

(defn -main                                                 ; static function
  [& args]
  (println "Os argumentos são: " (map valores-em args)))
