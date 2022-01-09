# ruilbieb

### install jdk8 (11 is not supported on the pi zero w)
sudo apt update
sudo apt install openjdk-8-jre

### install git
sudo apt install git


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

### run application at boot for ruilbieb
```
sudo cp /home/pi/git/ruilbieb/init.d/ruilbieb /etc/init.d/
sudo chmod +x /etc/init.d/ruilbieb
cd /etc/init.d/
sudo update-rc.d ruilbieb defaults
sudo reboot
```

### run application at boot for ruilbiebdisplay
```
sudo apt install i2c-tools

sudo cp /home/pi/git/ruilbieb/init.d/ruilbiebdisplay /etc/init.d/
sudo chmod +x /etc/init.d/ruilbiebdisplay
cd /etc/init.d/
sudo update-rc.d ruilbiebdisplay defaults
sudo reboot
```