strings=(
  domain
  soc-registry
  api-gateway
  common-service
  auth-service
  discovery-service
  payment-service
  user-service
)
mvn clean

# shellcheck disable=SC2164
for i in "${strings[@]}"; do
  # shellcheck disable=SC2164
  cd "$i"
  mvn clean package
  # shellcheck disable=SC2103
  cd ..
done