(ns sql-gen
  (:gen-class))
(use 'korma.core)
(require '[clojure.string :as str])

(declare users)
(defentity users
           (table :t_user_admin)
           (entity-fields :id :level_id :password :username :service_id))

(println
  (str/replace
    (sql-only (select users)), "\"", ""))

(def sql-str
  (sql-only
    (select users)))

(println
  (str/replace sql-str, "\"", ""))


(def users-map {:kyle {:date-joined "2009-01-01"
                       :number-pets 100
                       :summary
                                    {:average {:monthly 1000
                                               :yearly  12000}
                                     }
                       }
                }
  )


(println (vals users-map))


(println (map :number-pets (list {:number-pets 10},{:number-pets 20})))

(defn average-pets []
  (let [user-data (vals users-map)
        pet-counts (map :number-pets user-data)
        _ (println "total  pets:" pet-counts)
        total (apply + pet-counts)]
    (/ total (count users))))

(time average-pets)

(dotimes [x 5] (println "X is" x))

(defn string-length [^String x] (.length x))

(time (reduce + (map string-length (repeat 10000 "12345"))))

(defn transform-output [[k v]]
  {(keyword k) v})

(println (transform-output (list "name","value")))

(defmacro chain
  ([x form] `(. ~x ~form))
  ([x form & more] `(chain (. ~x ~form) ~@more)))

(defmacro bench [expr]
  `(let [start# (System/nanoTime) result# ~expr]
     {:result result# :elapsed (- (System/nanoTime) start#)}))

(println (bench (str "a" "b")))



