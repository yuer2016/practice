(ns sql-gen
  (:gen-class))

(use 'korma.core)
(require '[clojure.string :as str])

(declare users)
(defentity users
           (table :t_user_admin)
           (entity-fields :id :level_id :password :username :service_id))

(println (str/replace (sql-only (select users)) ,"\"", "" ))

