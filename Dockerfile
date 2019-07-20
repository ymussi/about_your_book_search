FROM clojure AS build-env
MAINTAINER Yuri Mussi <ymussi@gmail.com>

WORKDIR /usr/src/about-books
COPY project.clj /usr/src/about-books/
RUN lein deps
COPY . /usr/src/about-books

RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" about-books-standalone.jar

FROM openjdk:8-jre-stretch
WORKDIR /app

COPY --from=build-env /usr/src/about-books/about-books-standalone.jar /app/about-books.jar
COPY --from=build-env /usr/src/about-books/entrypoint.sh /app/entrypoint.sh
ENTRYPOINT ["/bin/sh", "/app/entrypoint.sh"]

