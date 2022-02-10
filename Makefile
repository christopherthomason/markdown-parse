CLASSPATH=lib/junit-4.12.2.jar:lib/hamcrest-core-1.3.jar:./

MarkdownParse.class: MarkdownParse.java
	javac -cp $CLASSPATH MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.class
	javac -cp @CLASSPATH MarkdownParseTest.java

tests: MarkdownParse.class MarkdownParseTest.class
	java -cp $CLASSPATH org.runner.JUnitCore MarkdownParseTest