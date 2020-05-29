FROM  maven:3.6-jdk-8-alpine

RUN mkdir -p /app/
COPY ./ /app/

CMD ["/app/docker-run.sh"]