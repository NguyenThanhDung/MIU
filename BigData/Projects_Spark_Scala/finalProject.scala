// Step 2: Select a categorical variable and a numeric variable and form the key-value pair and create a pairRDD called "population"

val csv = sc.textFile("OECDGas.csv")
val rowsWithHeader = csv.map(line => line.split(",").map(_.trim)).map(row => (row(1), row(3)))
val header = rowsWithHeader.first
val population = rowsWithHeader.filter(_._1 != header._1).map(row => (row._1.replaceAll("\"", ""), row._2.toDouble))
println("Step 2:")
population.foreach(println)


// Step 3: Compute the mean mpg and variance for each category

val populationGrouped = population.groupByKey()

def mean(i: Iterable[Double]) = i.sum.toDouble/i.count(_=>true)

def variance(i: Iterable[Double]) = { 
  val m = mean(i)
  val b = i.map(x => math.pow(x - m, 2))
  b.sum / (i.count(_=>true) - 1)
}

val populationMeanVariance = populationGrouped.map(x => (x._1, (mean(x._2), variance(x._2))))
println("Step 3:")
populationMeanVariance.foreach(println)


// Step 4: Create the sample for bootstrapping
val sample = population.sample(false, 0.25)
println("Step 4:")
sample.foreach(println)

// Step 5: Do 1000 times
var arrSample: Array[(String, (Double, Double))] = Array.empty[(String, (Double, Double))]

println("Step 5:")
var iterTimes = 10
for (i <- 1 to iterTimes) {
  // 5a. Create a "resampledData"
  val resampledDatasets = sample.sample(true, 1)
  
  // 5b. Compute the mean mpg and variance for each category
  val resampledDatasetsGrouped = resampledDatasets.groupByKey()
  val sampleMeanVariance = resampledDatasetsGrouped.map(x => (x._1, (mean(x._2), variance(x._2))))
  println("Loop " + i.toString + ":")
  sampleMeanVariance.foreach(println)
  
  // 5c. Adding the values
  arrSample = arrSample ++ sampleMeanVariance.collect()
}


// Step 6: Divide each quantity by 1000 to get the average
println("Step 6:")
import org.apache.spark.rdd.RDD
val rddSample: RDD[(String, (Double, Double))] = sc.parallelize(arrSample)
val rddSampleReduced = rddSample.reduceByKey((a, b) => (a._1 + b._1, a._2 + b._2))
val rddSampleAvg = rddSampleReduced.map(x => (x._1, (x._2 / iterTimes, x._2 / iterTimes)))
rddSampleAvg.foreach(println)