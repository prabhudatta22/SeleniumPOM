cd /d%~dp0
java -Dwebdriver.gecko.driver="E:\workspace\Automation\SeleniumGRID\geckodriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register -port 5568 -browser browserName=firefox

pause 1000