find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java com.amazurok.simulator.Simulator scenario.txt