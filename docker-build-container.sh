admin="sudo248dev/"
service="discovery-service:0.0.1"

# shellcheck disable=SC2046
docker rm -f $(docker container ps -a -q)
docker rmi "$admin$service"

echo "Build docker image $service"

image="${admin}$service"
folder=$(echo $service | cut -d':' -f 1)

# shellcheck disable=SC2164
cd "$folder"
docker build -t "${image}" .
# shellcheck disable=SC2103
cd ..
