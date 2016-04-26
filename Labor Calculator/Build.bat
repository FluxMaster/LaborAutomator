javac *.java
pause
jar cfm "Labor Calculator.jar" Manifest.txt *.class
jar cfm "../Labor Calculator.jar" Manifest.txt *.class
pause