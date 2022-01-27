### Description
Utility to parse a log file and generate reports to System.out (console). The reports incluide the following
- The number of unique IP addresses
- The top 3 most visited URLs
- The top 3 most active IP addresses

### Requirements
- JDK 11
- Maven 3.x

### To execute from the command line (bash) 
Maven will create an uber-jar in the target directory that is referred to by the run script.

Execute the following from the root of this git repo 
```
mvn install
./src/main/script/run.sh /some/where/example-data.log
```

### To execute from your IDE
Run... the Main class passing one argument containing the full path to a log file that you wish to parse
```
Example: /projects/log-file-parser/src/test/resources/programming-task-example-data.log 
```

### To execute the tests
Written in Junit, they will execute as part of mvn install, or on their own with the following command ...
```
$ mvn test
```