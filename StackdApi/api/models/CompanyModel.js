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

// comID: 5a1f4d4d45d19a0bc352f319
// recID: 5a1f4e0d45d19a0bc352f31a