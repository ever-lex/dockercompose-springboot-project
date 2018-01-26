FROM maven:3.5

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

CMD java -Dspring.profiles.active=${SPRING_PROFILES:-prod} ${JAVA_OPTS} -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}
