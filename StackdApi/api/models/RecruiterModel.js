var mongoose = require('mongoose');

var RecruiterSchema = new mongoose.Schema({
	cId : {
		type  : Schema.ObjectId, 
		ref   : 'Company'
	}
	firstName : String, 
	lastName  : String, 
	email     : String
});

module.export = mongoose.model('Recruiter', RecruiterSchema);