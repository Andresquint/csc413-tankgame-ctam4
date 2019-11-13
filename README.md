# csc413-tankgame

## Student Name  : Calvin Tam
## Student ID    : 917902523

### Player 1 control
- W: drive
- A: turn left
- D: turn right
- S: reverse
- Z: fire

### Player 2 control
- I: drive
- J: turn left
- L: turn right
- K: reverse
- M: fire

### Develop environment
- OpenJDK 11.0.5
- Working directory: root

### Compile to run
```bash
$ cd src \
&& javac tankgame/*.java tankgame/gameobject/*.java \
&& jar -cvfe ../jar/tankgame.jar tankgame.Launcher tankgame/*.class tankgame/gameobject/*.class ../resources/* \
&& rm tankgame/*.class tankgame/gameobject/*.class \
&& cd .. \
&& java -jar jar/tankgame.jar
```
