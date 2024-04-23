#!/bin/bash

echo "Killing all active user-service containers"

containers=$(docker ps | grep "ecomm-gateway" | awk '{print$1}')
if [ -n "$containers" ]
then
   docker kill $containers
   docker rm $containers
fi

aws --version

echo "Pulling image and starting container"

docker login -u AWS -p $(aws ecr get-login-password --region us-east-1) public.ecr.aws/j8h8n1c1
docker pull public.ecr.aws/j8h8n1c1/ecomm-gateway
docker run -it --env-file /home/ec2-user/docker/env_files/ecomm.env -p 8091:8091 -d public.ecr.aws/j8h8n1c1/ecomm-gateway