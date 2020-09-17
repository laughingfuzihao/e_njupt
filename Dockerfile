FROM java:8

COPY *.jar /NJUPT.jar

CMD ["--server.port=443"]

EXPOSE 7000

ENTRYPOINT ["java","-jar","/NJUPT.jar"]

# docker build -t njupt .
# docker run -d -e TZ="Asia/Shanghai" -p 443:443 --name njupt njupt
