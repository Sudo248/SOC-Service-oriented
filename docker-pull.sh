strings=(
  sudo248dev/soc-registry:0.0.1
  sudo248dev/api-gateway:0.0.1
  sudo248dev/common-service:0.0.1
  sudo248dev/auth-service:0.0.1
  sudo248dev/discovery-service:0.0.1
  sudo248dev/payment-service:0.0.1
  sudo248dev/user-service:0.0.1
)
for i in "${strings[@]}"; do
  echo "Pull docker image $i"
  docker pull "$i"
done

docker network create --subnet 172.18.0.0/24 --gateway 172.18.0.1 soc-network