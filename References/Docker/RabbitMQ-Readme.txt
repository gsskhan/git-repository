RabbitMQ
========


To run, simply use the following:

	$ docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3
	
	This will download and run RabbitMQ version 3.X

If you’d like to use the RabbitMQ management tool, then run this instead (which runs RabbitMQ and the management tool)
	$ docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

Note: Default login for the management UI is via a web browser using http://host:15672
      Obviously replacing the host with your server IP address. The default login and password are guest/guest.
      
      Example: http://localhost:15672