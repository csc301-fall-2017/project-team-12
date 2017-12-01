var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var RecruiterSchema = new mongoose.Schema({
	cId : {
		type  : Schema.ObjectId, 
		ref   : 'Company'
	},
	firstName : {
		type: String,
		required: true
	}, 
	lastName  : {
		type: String, 
		default: true
	},
	email : {
		type: String,
		required: true,
		unique: true 
	}, 
	resumes: [{
		type: Schema.ObjectId, 
		ref: 'Resume'
	}]
});

module.export = mongoose.model('Recruiter', RecruiterSchema);