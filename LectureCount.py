
import pyspark
from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("CS6502").getOrCreate()


timetables = spark.read.format("csv") \
    .option("header", True) \
    .load("/home/g20084447/ULTimeTableScraping/src/test/resources/dataset.csv",inferSchema=True)

timetables=timetables.filter(timetables['Type']=="LEC").select(timetables['Total Classes in Semester']).groupBy().sum().alias('CountLectures')
timetables.show()

