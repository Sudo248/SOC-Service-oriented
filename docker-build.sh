strings=(
  soc:0.0.2
  soc-registry:0.0.2
  api-gateway:0.0.2
  auth-service:0.0.2
  discovery-service:0.0.2
  payment-service:0.0.2
  user-service:0.0.2
  cart-service:0.0.2
  invoice-service:0.0.2
  promotion-service:0.0.2
  image-service:0.0.2
  notification-service:0.0.2
  chat-service:0.0.2
)
admin="sudo248dev/"
# shellcheck disable=SC2046
docker rm -f $(docker container ps -a -q)
for i in "${strings[@]}"; do
  docker rmi "$admin$i"
done

docker build -t "${admin}soc:0.0.2" .
count=0
for i in "${strings[@]}"; do
  image="${admin}$i"
  folder=$(echo $i | cut -d':' -f 1)
  echo "Build docker image: $i context: $folder"
  docker build -t "${image}" "${folder}"
  ((count=count+1))
done
echo "Total build $count docker image"
docker network create --subnet 172.18.0.0/16 --gateway 172.18.0.1 soc-network
