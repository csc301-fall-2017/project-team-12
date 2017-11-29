'use strict';
var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var TagSchema = new Schema({
	name: {
		type: String, 
		required: true, 
		unique: true
	}
});

module.export = mongoose.model('Tag', TagSchema);
