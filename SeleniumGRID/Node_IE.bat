cd /d%~dp0
java -Dwebdriver.ie.driver=IEDriverServer.exe -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register

pause 1000