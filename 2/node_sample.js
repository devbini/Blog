const express = require('express');
const app = express();
const port = 3000;

app.get('/ping', (req, res) => {
    res.send('PONG!');
});

app.get('/stress', (req, res) => {
    const loop = pareseInt(req.query.loop) || 100000;
    let sum = 0;

    for (let i = 0; i < loop; i++) {
        sum += i;
    }

    res.send(`stress test. Init: ${loop}, Sum: ${sum}`);
});

app.listen(port, () => {
    console.log(`port: ...`);
})