// Load the dataset into an RDD and split by commas
val data = sc.textFile("mtcars.csv") 
// Extract the header
val header = data.first()
// Filter out the header
val filteredData = data.filter(line => line != header)
// Split the data by commas
val splitData = filteredData.map(line => line.split(","))
// Extract the columns and convert numeric variable
val catNumRDD = splitData.map(columns => (columns(2).toInt, columns(1).toDouble)) 
val population = sc.parallelize(catNumRDD.collect())
// Print content
population.foreach(println)

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

// Create a bootstrap sample by randomly sampling 25% of the population without replacement
val sample = population.sample(false, 0.25)
// Print the contents of the sample RDD
sample.foreach(println)
