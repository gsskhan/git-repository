### How to Run

#### Start services in this order:
* service-registry
* config-server
* user-service, product-service
* order-service
* api-gateway

### Eureka Dashboard
* [http://localhost:8761](http://localhost:8761)

### Test via API Gateway (Best Practice)
* Endpoint: GET [http://localhost:8080/users/1](http://localhost:8080/users/1)