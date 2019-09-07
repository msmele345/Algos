FROM redis

RUN mkdir -p /usr/local/etc/redis

COPY ./redis.conf /usr/local/etc/redis/redis.conf

EXPOSE 6379

CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]


#build out redisClient with commands
#exec into container and set in some objects