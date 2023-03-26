#strings=(
#    soc-registry
#    api-gateway
#    common-service
#    auth-service
#)
#version=0.0.1
#
#for i in "${strings[@]}"; do
#    docker rmi "$i:$version"
#done
#INPUT='registry:0.0.1'
#SUBSTRING=$(echo $INPUT| cut -d':' -f 1)
#echo $SUBSTRING
a="duong"
b="Oanh"
echo "${a}b"