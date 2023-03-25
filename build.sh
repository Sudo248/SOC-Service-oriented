#docker rm -f $(docker container ps -a -q)
docker rmi soc-registry:0.0.1
docker rmi api-gateway:0.0.1
docker rmi common-service:0.0.1
docker rmi auth-service:0.0.1

mvn clean

# shellcheck disable=SC2164
cd domain
mvn clean package

# shellcheck disable=SC2103
cd ..
# shellcheck disable=SC2164
cd soc-registry
mvn clean package

cd ..
cd api-gateway
mvn clean package

cd ..
cd common-service
mvn clean package

cd ..
cd auth-service
mvn clean package