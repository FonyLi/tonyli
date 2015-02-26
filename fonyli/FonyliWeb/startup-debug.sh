

for i in "$CLASSPATH"./libs/*.jar
do
 CLASSPATH="$CLASSPATH":"$i"
done

export CLASSPATH=.:$CLASSPATH
echo ${CLASSPATH}
java -Xdebug -Xrunjdwp:transport=dt_socket,address=2080,server=y,suspend=y -Xms50m -Xmx250m com.fonyli.tonyliweb.server.MainService &


