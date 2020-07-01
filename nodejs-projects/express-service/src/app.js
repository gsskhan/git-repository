/**
 * Express JS based application
 */

// Constants Defined
const express = require('express')
const app = express()
const port = 8080

// Routes
app.get('/', function (req, res) {
    console.log('Received GET request ...')
    res.send('<h1>Hello! Welcome to application</h1>')
})

app.post('/', function (req, res) {
    console.log('Received POST request ...')
    res.send('Got a POST request')
})

app.put('/user', function (req, res) {
    console.log('Received PUT request ...')
    res.send('Got a PUT request at /user')
})

app.delete('/user', function (req, res) {
    console.log('Received DELETE request ...')
    res.send('Got a DELETE request at /user')
})

// Startup declaration
app.listen(port, () => {
    console.log(`Express app listening at http://localhost:${port}`)
})