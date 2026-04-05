### How to Run

#### Start services in this order:
* service-registry
* config-server
* user-service, product-service
* order-service
* api-gateway

### Eureka Dashboard
* [http://localhost:8761](http://localhost:8761)

### Config Server
Examples:
You can access them via the Config Server URL:
* http://localhost:8081/order-service/default
* http://localhost:8081/order-service/dev

### Test via API Gateway (Best Practice)
* Endpoint: GET [http://localhost:8080/users/1](http://localhost:8080/users/1)
* Endpoint: GET [http://localhost:8080/orders/1](http://localhost:8080/orders/1)
* Endpoint: GET [http://localhost:8080/products/1](http://localhost:8080/products/1)