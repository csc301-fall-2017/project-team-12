'use strict';
var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var CompanySchema = new Schema({
	name: {
		type: String, 
		required: true, 
		unique: true
	},
	recruiters: [{
		type: Schema.ObjectId, 
		ref: 'Recruiter'
	}]
});

module.export = mongoose.model('Company', CompanySchema);