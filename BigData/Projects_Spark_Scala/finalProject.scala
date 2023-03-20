// Step 2: Select a categorical variable and a numeric variable and form the key-value pair and create a pairRDD called "population"

val csv = sc.textFile("OECDGas.csv")
val rowsWithHeader = csv.map(line => line.split(",").map(_.trim)).map(row => (row(1), row(3)))
val header = rowsWithHeader.first
val population = rowsWithHeader.filter(_._1 != header._1).map(row => (row._1.replaceAll("\"", ""), row._2.toDouble))
val populationGrouped = population.groupByKey()


// Step 3: Compute the mean mpg and variance for each category

def mean(i: Iterable[Double]) = i.sum.toDouble/i.count(_=>true)

def variance(i: Iterable[Double]) = { 
  val m = mean(i)
  val b = i.map(x => math.pow(x - m, 2))
  b.sum / (i.count(_=>true) - 1)
}

val populationMeanVariance = populationGrouped.map(x => (x._1, (mean(x._2), variance(x._2))))


// Step 4: Create the sample for bootstrapping
val sample = population.sample(false, 0.25)


// Step 5: Do 1000 times
var arrSample: Array[(String, (Double, Double))] = Array.empty[(String, (Double, Double))]

for (i <- 1 to 1000) {
  // 5a. Create a "resampledData"
  val resampledDatasets = sample.sample(true, 1)
  
  // 5b. Compute the mean mpg and variance for each category
  val resampledDatasetsGrouped = resampledDatasets.groupByKey()
  val sampleMeanVariance = resampledDatasetsGrouped.map(x => (x._1, (mean(x._2), variance(x._2))))
  
  // 5c. Adding the values
  arrSample = arrSample ++ sampleMeanVariance.collect()
}


// Step 6: Divide each quantity by 1000 to get the average
val arrSampleAvg = arrSample.map(x => (x._1, (x._2._1 / 1000, x._2._2 / 1000)))
import org.apache.spark.rdd.RDD
val rddSampleAvg: RDD[(String, (Double, Double))] = sc.parallelize(arrSampleAvg)
val rddSampleAvgReduced = rddSampleAvg.reduceByKey((a, b) => (a._1 + b._1, a._2 + b._2))
