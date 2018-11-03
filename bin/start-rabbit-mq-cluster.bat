c:

cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.7\sbin


set RABBITMQ_NODE_PORT=5672 
set RABBITMQ_NODENAME=rabbit 
start "Node-rabbit" rabbitmq-server -rabbitmq_management listener [{port,15672}]
call rabbitmq-plugins enable rabbitmq_management
call rabbitmqctl -n rabbit stop_app
call rabbitmqctl -n rabbit start_app

timeout /t 5

set RABBITMQ_NODE_PORT=5673 
set RABBITMQ_NODENAME=hare 
start "Node-hare" rabbitmq-server -rabbitmq_management listener [{port,15673}]

timeout /t 10

set RABBITMQ_NODE_PORT=5674 
set RABBITMQ_NODENAME=micky 
start "Node-micky" rabbitmq-server -rabbitmq_management listener [{port,15674}]

timeout /t 5

set RABBITMQ_NODE_PORT=5675 
set RABBITMQ_NODENAME=minni 
start "Node-minni" rabbitmq-server -rabbitmq_management listener [{port,15675}]

timeout /t 20

call rabbitmqctl -n hare stop_app
call rabbitmqctl -n hare join_cluster rabbit@DESKTOP-IO9KB8M
call rabbitmqctl -n hare start_app

call rabbitmqctl -n micky stop_app
call rabbitmqctl -n micky join_cluster rabbit@DESKTOP-IO9KB8M
call rabbitmqctl -n micky start_app

call rabbitmqctl -n minni stop_app
call rabbitmqctl -n minni join_cluster rabbit@DESKTOP-IO9KB8M
call rabbitmqctl -n minni start_app

call rabbitmqctl -n rabbit@DESKTOP-IO9KB8M set_policy "ha-failover" ".*" "{\"ha-mode\":\"all\", \"ha-promote-on-shutdown\":\"always\", \"ha-promote-on-failure\":\"always\", \"queue-master-locator\":\"random\", \"ha-sync-mode\":\"automatic\"}"

timeout /t 10000