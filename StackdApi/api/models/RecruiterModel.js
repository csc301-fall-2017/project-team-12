var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var RecruiterSchema = new mongoose.Schema({
	cId : {
		type  : Schema.ObjectId,
		ref   : 'Company'
	},
	firstName : {
		type : String
	},
	lastName  : {
		type : String
	},
	email     : {
		type: String
	}
});

module.export = mongoose.model('Recruiter', RecruiterSchema);