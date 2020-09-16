FROM  maven:3.6-jdk-8-alpine

RUN mkdir -p /app/
COPY ./ /app/
RUN chmod +x /app/docker-run.sh

CMD ["/app/docker-run.sh"]