strings=(
  soc-registry:0.0.1
  api-gateway:0.0.1
  common-service:0.0.1
  auth-service:0.0.1
)
admin="sudo248dev/"
# shellcheck disable=SC2046
docker rm -f $(docker container ps -a -q)
for i in "${strings[@]}"; do
  docker rmi "$admin$i"
done

docker build -t "${admin}soc:0.0.1" .

for i in "${strings[@]}"; do
  echo "Build docker image $i"
  # shellcheck disable=SC2034
  image="${admin}$i"

  folder=$(echo $i | cut -d':' -f 1)
  # shellcheck disable=SC2164
  cd "$folder"
  docker bulild -t image .
  # shellcheck disable=SC2103
  cd ..

done

docker network create --subnet 172.18.0.0/24 --gateway 172.18.0.1 soc-network
