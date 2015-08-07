(ns simple-math)

(defn read-int [pos]
	(println "What is the" pos "number?")
	(bigint (read-line)))

(let [first-number (read-int "first")]
	(let [second-number (read-int "second")]
		(println (+ first-number second-number))
		(println (- first-number second-number))
		(println (* first-number second-number))
		(println (/ first-number second-number))))
