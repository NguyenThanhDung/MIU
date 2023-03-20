// Load the dataset into an RDD and split by commas
val data = sc.textFile("mtcars.csv") 
// Extract the header
val header = data.first()
// Filter out the header
val filteredData = data.filter(line => line != header)
// Split the data by commas
val splitData = filteredData.map(line => line.split(","))

// Step 2. Select a categorical variable and a numeric variable and form the key-value pair and create a pairRDD called “population”.
// Extract the columns and convert numeric variable
val catNumRDD = splitData.map(columns => (columns(2).toInt, columns(1).toDouble)) 
val population = sc.parallelize(catNumRDD.collect())
// Print content
population.foreach(println)

// Step 3. Compute the mean mpg and variance for each category and display
// Group by the categorical variable
val groupedRDD = catNumRDD.groupByKey()
// Compute the mean and variance for each category
val statsRDD = groupedRDD.mapValues(data => {
  val mean = data.sum / data.size
  val variance = data.map(x => math.pow(x - mean, 2)).sum / data.size
  (mean, variance)
})
// Print the contents of the statsRDD RDD
statsRDD.foreach(println)

// Step 4. Create the sample for bootstrapping. All you need to do is take 25% of the population without replacement.
val sample = population.sample(false, 0.25)
// Print the contents of the sample RDD
sample.foreach(println)

// 5a. Create a "resampledData". All you need to do is take 100% of the sample with replacement.
val resampledData = sample.takeSample(true, 1)

// 5b. Compute the mean mpg and variance for each category

// Making RDD from parallelized collections:
val resampledDataRDD = sc.parallelize(resampledData)
println("resampledDataRDD:")
resampledDataRDD.foreach(println)

// Group by the categorical variable
val resampledGroupedRDD = resampledDataRDD.groupByKey()
println("resampledGroupedRDD:")
resampledGroupedRDD.foreach(println)

// Compute the mean and variance for each category
val resampledStatsRDD = resampledGroupedRDD.mapValues(data => {
  val mean = data.sum / data.size
  val variance = data.map(x => math.pow(x - mean, 2)).sum / data.size
  (mean, variance)
})
// Print the contents of the statsRDD RDD
println("resampledStatsRDD:")
resampledStatsRDD.foreach(println)