'use strict';
var mongoose = require('mongoose');

var Company = mongoose.model('Company');

/* Get the all the tags for all the companies */
exports.getAllCompanies = function(req, res) {
	Company.find().populate({
		path: 'recruiters',
		populate: {
			path: 'resumes',
			model: 'Resume',
			populate: {
				path: 'tags',
				model: 'Tag'
			}
		}
	}).exec(function(err, task) {
		if(err) 
			res.send(err);
		res.json({companies: task});
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

exports.addRecruiterToCompany = function(req, res) {
	Company.update(
		{ _id: req.params.companyId }, 
		{ $push: { recruiters: req.body.recruiterId }}, function(err, task) {
			if(err) res.send(err);
			res.json(task);
		});
};


