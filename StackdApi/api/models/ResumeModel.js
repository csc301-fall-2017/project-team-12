'use strict';
var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var ResumeSchema = new Schema({ 
	candidateName: {
		type: String, 
		required: true
	},
	collectionDate: {
		type: String, 
		default: new Date().toLocaleDateString()
	}, 
	collectionTime: {
		type: String, 
		default: new Date().toLocaleTimeString()
	}, 
	recruiterComments: {
		type: String, 
		required: true 
	},
	/* list of of tags associated to the resume */
	tags: [{
		type: Schema.ObjectId, 
		ref: 'Tag'
	}],  
	rating: {
		type: Number, 
		min: 0, 
		max: 10, 
		required: true
	}
});

module.export = mongoose.model('Resume', ResumeSchema);