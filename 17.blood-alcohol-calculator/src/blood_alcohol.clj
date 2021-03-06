(ns blood-alcohol)

(defn read-input [question]
  (println question)
  (read-line))

(defn read-gender [question]
  (println question)
  (re-find #"w|m|W|M" (read-line)))

(defn to-int-or-nil [chs] (if (re-matches #"[0-9]+" chs) (bigint chs) nil))

(defn read-number [question]
  (->> (read-input question)
       (to-int-or-nil)))

(defn round2
  "Round a double to the given precision (number of significant digits)"
  [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))

(defn bac-calc [alcohol weight gender last-time]
  (if (= gender nil) nil
                     (let [ratio (if (= (.toLowerCase gender) "m") 0.73 0.66)]
                       (->> (- (/ (* alcohol 5.14) (* weight ratio)) (* last-time 0.015))
                            (bigdec)
                            (round2 2))
                       )))

(let [alcohol (read-number "How many alcohol did you consume in oz?")
      weight (read-number "What is your weight?")
      gender (read-gender "What is your gender (M/W)?")
      last-time (read-number "How many hours passed since last drink?")
      bac (bac-calc alcohol weight gender last-time)]
  (println (str "alcohol:" alcohol " weight:" weight " gender:" gender " last-time:" last-time))
  (println (str "your bac is:" bac "."))
  (if (= bac nil)
    (println "something went wrong")
    (if (> bac 0.08) (println "It is not legal for you to drive."))
  )
  )