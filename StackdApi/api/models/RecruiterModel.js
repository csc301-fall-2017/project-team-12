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

// resumeID: 5a1f6151be7e3d0d0c5dd080
// tagID: 5a1f60ffbe7e3d0d0c5dd07f
// 