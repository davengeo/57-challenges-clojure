(ns tax-calculator)

(defn read-bigdec [question]
  (println question)
  (bigdec (read-line)))

(defn read-string [question]
  (println question)
  (read-line))

(def ^:const TWO_DIGITS 100)

(defn round-up [amount]
  (bigdec (/ (Math/ceil (* amount TWO_DIGITS)) TWO_DIGITS)))

(def state-taxes {:WI 0.055 :LI 0.077})

(defn tax [amount state]
  (let [rate (state-taxes (keyword state))]
    (if (= nil rate) 0 (round-up (* amount rate)))))

(let [amount (read-bigdec "What is the order amount?")
      state (read-string "What is the state?")
      taxes (tax amount state)]

  (if (> taxes 0)
    (do
      (println (str "The subtotal is $" amount))
      (println (str "The tax is $" taxes))
      ))

  (println (str "The total is $" (+ amount taxes)))
  )