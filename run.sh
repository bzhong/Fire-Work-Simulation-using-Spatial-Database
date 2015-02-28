javac -classpath /path/to/your/ojdbc6.jar:/path/to/your/sdoapi.jar mainProgram/Populate.java mainProgram/HW3.java mainProgram/HW3GUI.java
cp map.jpg mainProgram/
jar -cvf hw3.jar mainProgram/*.class mainProgram/map.jpg
java -classpath /path/to/your/ojdbc6.jar:/path/to/your/sdoapi.jar:hw3.jar mainProgram.HW3GUI /path/to/your/building.xy /path/to/your/hydrant.xy /path/to/your/firebuilding.txt
