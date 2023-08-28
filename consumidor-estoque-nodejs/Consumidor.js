const amqp = require('amqplib')

const fila = 'ESTOQUE'
amqp.connect({
    host: 'localhost',
    port: 5672,
    username: 'guest',
    password: 'guest'
}).then((conn) => {
    conn.createChannel()
    .then((channel) => {
        channel.consume(fila, (msg) => {
            console.log(msg.content.toString())
        }, {
            noAck: true
        })
    })
    .catch((e) => console.log(e))
}).catch((e) => console.log(e))