Implementing some of the Grails guides with Grails 5

To run the RabbitMQ example (\book\show\? and \bookViewPageView) you need to fire up a Dockerized RabbitMQ instance with:
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15671:15671 -p 25672:25672 rabbitmq:3-management
and set enabled: true for rabbit config in application.yml





