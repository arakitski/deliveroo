## Deliveroo test task
### Overview
A command line application which parses a cron string and expands each field
to show the times at which it will run.

The output should be formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.
For example, the following input argument:

`*/15 0 1,15 * 1-5 /usr/bin/find
`

Should yield the following output:

```
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find
```
### How to run
**To test application you must have Java version 11 or higher installed**
If you don't have installed java, you need to install it first https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html

After that you can run app from compiled jar
```
java -jar lib\deliveroo-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

[test]: https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html

### How to install
To create a new jar you need to have installed maven.

To compile and create jar 
```
mvn clean install
```
Jar will be in target/deliveroo-1.0-SNAPSHOT.jar