

for i in "$CLASSPATH"./libs/*.jar
do
 CLASSPATH="$CLASSPATH":"$i"
done

export CLASSPATH=.:$CLASSPATH
echo ${CLASSPATH}
java -Xms50m -Xmx250m com.fonyli.tonyliweb.server.MainService &


