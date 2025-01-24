# Spring Showcase

## Used technologies
- Java 17
- Spring Boot
- Spring Data JPA for custom atomic database queries (look at [Atomic Increment/Decrement](./src/main/java/com/portfolio/showcase_spring/Address/AddressRepo.java))
- Artemis for asynchronous and partition-grouped communication inside a cluster
- Docker Swarm for containers orchestration
- Gradle for building and running the project

## Running
```bash
./gradlew build
docker swarm init --advertise-addr <available-ip>
docker stack deploy --with-registry-auth -c docker-compose.yml showcase_spring

# After rebuilding spring application update the service
docker service update showcase_spring_java-service
```