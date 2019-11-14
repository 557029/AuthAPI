authservice/authenticator
docker network create mynet

docker run --network mynet --name container-1 ...
docker run --network mynet --name container-2 ...

docker run -d -p 8082:8082 --name authapi authservice/authenticator

docker rmi $(docker images -f "dangling=true" -q)

kubectl run authapi --image=ialeshin/authenticator
kubectl expose deployment authapi --type=LoadBalancer --port=8082
