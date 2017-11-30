'use strict';
var mongoose = require('mongoose');

var Company = mongoose.model('Company');

/* Get the all the tags for all the companies */
exports.getAllCompanies = function(req, res) {
	Company.find({}, function(err, task) {
		if(err) 
			res.send(err);
		res.json(task);
	});
};

/* Insert a new tag for a company */
exports.insertCompany = function(req, res) {
	var company = new Company(req.body);
	company.save(function(err, task) {
		if(err) 
			res.send(err);
		res.json(task);
	});
};

/* Remove a tag with a given tag id */
exports.removeCompany = function(req, res) {
	Company.remove({_id: req.params.companyId}, function(err, task) {
		if(err)
			res.send(err);
		res.json({message: 'Successfully deleted the tag'});
	});
};



