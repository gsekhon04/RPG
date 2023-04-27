default: Map.java Room.java Inventory.java RPGEngine.java
	javac Map.java Room.java Inventory.java RPGEngine.java

run: Map.class Room.class Inventory.class RPGEngine.class
	java RPGEngine

clean:
	rm -f *.class