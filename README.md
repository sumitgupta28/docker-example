# docker-example

# Docker build.

1. This application is using the docker stage to build via maven.
2. if you would like to build the application , use below command

```shell
    docker build .
```
3. otherwise, you can use docker-compose to build and run the application.

```shell
    docker-compose build
    docker-compose up
```
or use the following command
```shell
docker-compose.yml up -d

```

3. validation via swagger
http://localhost:8080/employee-service/swagger-ui.html#
