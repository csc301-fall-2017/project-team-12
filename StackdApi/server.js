var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');

/* Import all the models */
var Tag = require('./api/models/TagModel');

/* Initialize express and the port number */
var app = express(); 
var port = process.env.PORT || 3000;

/* Open the db connection */
var url = 'mongodb://localhost:27017/StackdDb';
mongoose.connect(url, {
	useMongoClient: true,
	autoReconnect: true, 
	reconnectTries: Number.MAX_VALUE, 
	reconnectInterval: 500, 
	poolSize: 10, 
}).then(
	() => console.log('Sucessfully connected to the database...'),
	err => console.log(err.stack)
);

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

/* Register the routes to this app */
var routes = require('./api/routes/TagRoutes');
routes(app);

/* Listen to the user requests on the given port */
app.listen(port, () => {
	console.log('StackdApi server is running on port ' + port + '...');
});

