var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');

/* Import all the models */
var Tag = require('./api/models/TagModel');
var Resume = require('./api/models/ResumeModel');
var Recruiter = require('./api/models/RecruiterModel');
var Company = require('./api/models/CompanyModel');

/* Initialize express and the port number */
var app = express(); 
var port = process.env.PORT || 3000;

/* Open the db connection */
var url = 'mongodb://stackd:password@ds031407.mlab.com:31407/stackd';
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
var TagRoutes = require('./api/routes/TagRoutes');
var ResumeRoutes = require('./api/routes/ResumeRoutes');
var RecruiterRoutes = require('./api/routes/RecruiterRoutes');
var CompanyRoutes = require('./api/routes/CompanyRoutes');

TagRoutes(app);
ResumeRoutes(app);
RecruiterRoutes(app);
CompanyRoutes(app);

/* Listen to the user requests on the given port */
app.listen(port, () => {
	console.log('StackdApi server is running on port ' + port + '...');
});

