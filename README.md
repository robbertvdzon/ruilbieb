# ruilbieb

sudo apt update
sudo apt remove openjdk-11-jdk
sudo apt remove openjdk-11-jre
sudo apt remove openjdk-11-jre-headless
sudo apt autoremove
sudo apt install openjdk-8-jre


cd
mkdir git
cd git
git clone https://github.com/WiringPi/WiringPi
cd WiringPi/
./build

cd ~/git
git clone https://github.com/robbertvdzon/ruilbieb.git
cd ruilbieb
./build.sh
./run.sh


