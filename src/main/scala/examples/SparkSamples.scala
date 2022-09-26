package examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions.{col, lit, when}
import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructType}


case class Empleado(firstname: String, middlename: String, lastname: String, dob: String, gender: String, salary: Long)

object SparkSamples {

  def main(args: Array[String]): Unit = {
    // builds spark session using master node by name, if exists returns current existing session
    val spark : SparkSession = SparkSession
        .builder()
        .master("local[1]")
        .appName("Spark On Scala Examples")
        .getOrCreate()

    // imports all implicit objects from current session
    import spark.implicits._

    val data = Seq(
          ("jose", "Antonio", "Beas", "1987-09-18", "M", 3000),
          ("Juan", "", "Bustos", "1990-05-19", "M", 4000),
          ("Foy", "", "Barrantes", "1990-09-05", "M", 4000),
          ("Elias", "", "Sanchez", "1990-12-01", "F", 4000),
          ("Daniel", "", "Diaz", "1990-02-17", "F", 5000)
      )

    val columns = Seq("firstname", "middlename", "lastname", "dob", "gender", "salary")

    // creates a dataframe using data from data seq and columns names from columns seq
    val df = spark.sparkContext
        .parallelize(data)
        .toDF(columns : _*)
    df.show(20)


    //defines seq of case classes objects
    val dataCaseClass = Seq(
          Empleado("jose", "Antonio", "Beas", "1987-09-18", "M", 3000),
          Empleado("Juan", "", "Bustos", "1990-05-19", "M", 4000),
          Empleado("Foy", "", "Barrantes", "1990-09-05", "M", 4000),
          Empleado("Elias", "", "Sanchez", "1990-12-01", "M", 4000),
          Empleado("Daniel", "", "Diaz", "1990-02-17", "M", 5000)
      )

    val dfCaseClasses = spark // refers to current session
        .sparkContext // refers to entire spark infra
        .parallelize(dataCaseClass) // uses distributed nodes to read the data
        .toDF() // this is an implicit function that reads col names and types from case class definition

    dfCaseClasses.show(20) // displays first 20 rows or all available data in case less than 20
    dfCaseClasses.show() // displays first 20 rows or all available data in case less than 20

    //performs an union from 2 datasets, since they are immutable, no changes are applied to first 2 and result are stored in a new dataframe
    val dfUnion = df.union(dfCaseClasses)
    dfUnion.show(20)

    //filter dataframe rows using multiple ways
    val dfUnique = dfUnion
        .filter($"salary" > 3000) //using $ implicit import
        .filter(col("salary") < 50000) //using col function
        .filter(dfUnion("salary") < 10000) //using col name from specific dataframe
        .filter("gender == 'M'") // using sql expression

    dfUnique
      .withColumnRenamed("gender", "Gender") //renames columns
      .withColumn("language", lit("ES")) //creates a new column for each row
      .withColumn("new_gender",
          when(col("gender") === "M","Male")
          .when(col("gender") === "F","Female")
          .otherwise("Unknown")
      ) // creates a new column with chained conditions
      .withColumn("new_gender_2",
        when(col("gender") === "M","Male")
            .otherwise(
                when(col("gender") === "F","Female")
                .otherwise("Unknown")
            )
      ) // creates a new column with nested conditions
      .show(20)


    //writing result dataset/dataframe/

    dfUnique.write.orc("results/orc/save.orc")

    dfUnique.write.format("orc").save("results/orc/write.orc")



    //prints dataframe schema with default schema
    df.printSchema()

    //create raw rdd
    val rdd = spark.sparkContext.parallelize(data)

    //creates dataframe with default schema
    val dfWitDefaultSchema = spark.createDataFrame(rdd)
    dfWitDefaultSchema.printSchema()
    //since newer spark versions only supports RDD of Row, creating RDD[Row] object
    val rowRDD : RDD[Row] = rdd.map(t => Row(t._1, t._2, t._3, t._4, t._5, t._6))

    //structured schema
    val arrayStructureSchema = new StructType()
      .add("firstname", StringType)
      .add("middlename",StringType)
      .add("lastname", StringType)
      .add("dob", StringType)
      .add("gender", StringType)
      .add("salary", LongType)

    //creates dataframe with schema
    val dfWithSchema: DataFrame = spark.createDataFrame(rowRDD, arrayStructureSchema)
    dfWithSchema.printSchema()

    dfWithSchema
      .select( //select elements using col function
          col("firstname"),
          col("middlename"),
          col("lastname"),
          col("salary").cast(IntegerType) //explicit casting
      )
      .printSchema()

    dfWithSchema
      .select( //select elements using col name
          "firstname",
          "middlename",
          "lastname",
          "salary"
      )
      .printSchema()

    dfWithSchema
        .select( //select elements using $ implicit
            $"firstname",
            $"middlename",
            $"lastname",
            $"salary"
        )
        .printSchema()

    val dfFromCSV = spark
      .read
      .option("header", true)
      .csv("src/main/scala/examples/data.csv")

    dfFromCSV.printSchema()

    val dfFromCSVWithHeader = spark
      .read
      .option("header", false)
      .csv("src/main/scala/examples/data_no_header.csv")

    dfFromCSV.printSchema()

    val dfFromCSVWithSchema = spark
        .read
        .option("header", true)
        .schema(arrayStructureSchema)
        .csv("src/main/scala/examples/data.csv")

    dfFromCSVWithSchema.printSchema()

    //in case we reading from s3 or s3n
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "awsAccessKeyId value")
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "awsSecretAccessKey value")
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3n.endpoint", "s3.amazonaws.com")

    spark.sparkContext.textFile("s3a://scalaSpark/csv/*") //supports wildcards




  }

}