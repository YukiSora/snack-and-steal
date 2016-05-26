JC = javac
.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
		Driver.java \
		Director.java \
		Sprite.java \
		Character.java \
		Map.java \
		Player.java \
		Guard.java \
		OwnPlayer.java \
		OthersPlayer.java \
		Snack.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
