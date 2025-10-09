mkdir execute
move target\software.jar execute\
cd execute
java -Djava.library.path=. -jar software.jar
pause