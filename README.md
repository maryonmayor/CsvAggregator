# CsvAggregator

### Description

aggregate 2 csv file with same name to create a new csv file.

### how to run?

1. build the src *make sure you have java and mvn isntalled*

`mvn clean install`

2. configure the config.properties and put the path in the csv1, csv2, and aggregated
3. run the jar file

`java -jar CsvAggregator-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

4. put the first csv file inside csv1 folder. 
5. put the second csv file inside csv2 folder.*make sure the filename is the same*
6. wait for 10s *timeout set to know when to aggregate the 2 file*
7. check the aggregate folder.
