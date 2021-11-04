#!/bin/bash
sudo yum update -y
sudo yum -y install git
sudo amazon-linux-extras install docker
sudo service docker start
sudo usermod -a -G docker ec2-user
git clone https://github.com/gfiallos/TBSCG.git
cd TBSCG/notesapp/src/deployment
docker swarm init
sh deploy.sh