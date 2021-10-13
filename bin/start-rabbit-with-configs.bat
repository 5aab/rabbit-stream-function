c:

cd %AppData%

rm -rf RabbitMQ

cd C:\FAST\rabbit\3.8.11\sbin

path=C:\Windows\System32

@REM rabbitmq-plugins --offline enable [plugin name]
call rabbitmq-plugins enable rabbitmq_management
call rabbitmq-plugins enable rabbitmq_prometheus
call rabbitmq-plugins enable rabbitmq_auth_backend_oauth2

@REM call rabbitmq-plugins enable prometheus_rabbitmq_exporter
@REM call rabbitmq-plugins enable rabbitmq_shovel rabbitmq_shovel_management
@REM call rabbitmq-plugins enable rabbitmq_jms_topic_exchange

set RABBITMQ_CONFIG_FILE=D:\ws\rabbit-stream-function\bin\zone1-node1
set RABBITMQ_NODENAME=zone1-node1
set RABBITMQ_NODE_PORT=5692
start "zone1-node1" rabbitmq-server

timeout /t 20

set RABBITMQ_CONFIG_FILE=D:\ws\rabbit-stream-function\bin\zone1-node2
set RABBITMQ_NODENAME=zone1-node2
set RABBITMQ_NODE_PORT=5693
start "zone1-node2" rabbitmq-server

timeout /t 20

set RABBITMQ_CONFIG_FILE=D:\ws\rabbit-stream-function\bin\zone2-node1
set RABBITMQ_NODENAME=zone2-node1
set RABBITMQ_NODE_PORT=5694
start "zone2-node1" rabbitmq-server

timeout /t 20

set RABBITMQ_CONFIG_FILE=D:\ws\rabbit-stream-function\bin\zone2-node2
set RABBITMQ_NODENAME=zone2-node2
set RABBITMQ_NODE_PORT=5695
start "zone2-node2" rabbitmq-server

timeout /t 20


set RABBITMQ_CONFIG FILE=I: \rabbit-mg\zabbitmq-zonel-node2 set RABBITMQ NODENAME=zonel-node2 set RABBITMQ_NODE PORT=5673 start "zone1-node2" rabbitmq-server
timeout /t 20
set RABBITMQ_CONFIG FILE=I: \rabbit-mg\zabbitmq-zone2-nodel set RABBITMQ NODENAME=zone2-nodel set RABBITMQ_NODE_PORT=5674 start "zone2-node1" rabbitmq-server
timeout /t 10
set RABBITMQ_CONFIG FILE=I: \rabbit-mg\zabbitmq-zone2-node2 set RABBITMQ NODENAME=zone2-node2 set RABBITMQ NODE PORT=5675 start "zone2-node2" rabbitmq-server
timeout lt 35
call rabbitmqctl -n zonel-node1 delete_vhost /
timeout /t 10000