# ruilbieb

### install jdk8 (11 is not supported on the pi zero w)
sudo apt update
sudo apt remove openjdk-11-jdk
sudo apt remove openjdk-11-jre
sudo apt remove openjdk-11-jre-headless
sudo apt autoremove
sudo apt install openjdk-8-jre


### clone and build WiringPi (needed for the pi4j library)
```
cd
mkdir git
cd git
git clone https://github.com/WiringPi/WiringPi
cd WiringPi/
./build
```

### clone build application
```
cd ~/git
git clone https://github.com/robbertvdzon/ruilbieb.git
cd ruilbieb
./build.sh
./run.sh
```

### run application at boot
```
sudo cp /home/pi/git/ruilbieb/init.d/ruilbieb /etc/init.d/
sudo chmod +x /etc/init.d/ruilbieb
cd /etc/init.d/
sudo update-rc.d ruilbieb.py defaults
sudo reboot
```