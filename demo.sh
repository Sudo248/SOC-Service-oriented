strings=(
    soc-registry
    api-gateway
    common-service
    auth-service
)
version=0.0.1

for i in "${strings[@]}"; do
    docker rmi "$i:$version"
done