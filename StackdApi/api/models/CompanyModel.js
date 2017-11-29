'use strict';
var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var CompanySchema = new Schema({
	name: {
		type: String, 
		required: true, 
		unique: true
	}
});

module.export = mongoose.model('Company', CompanySchema);