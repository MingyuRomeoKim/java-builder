FROM php:8.1-cli-alpine
RUN apk update
RUN apk add php81-simplexml
RUN apk add php81-fileinfo
RUN apk add php81-dom
RUN apk add php81-curl
RUN apk add php81-tokenizer
RUN apk add php81-xmlwriter
RUN apk add php81-xml
RUN apk add php81-session
RUN apk add php81-pdo
RUN apk add php81-xmlreader
RUN apk add php81-sodium

RUN apk add composer
RUN composer self-update

CMD ["/bin/sh"]

# docker build --rm --force-rm --tag "php:8.1-cli-alpine" .
# docker run --rm -it --name php-8.1-cli-alpine php:8.1-cli-alpine
