'use strict';
var mongoose = require('mongoose');

var Recruiter = mongoose.model('Recruiter');

exports.getAllRecruiters = function (req, res) {
	Recruiter.find().populate({
		path: 'resumes',
		populate: {
			path: 'tags', 
			model: 'Tag'
		}
	}).exec(function(err, task) {
		if(err) res.send(err);
		res.json({ recruiters: task });
	});
};

exports.addRecruiter = function (req, res) {
	var recruiter = new Recruiter(req.body);
	recruiter.save(function(err, task) {
		if(err) res.send(err);
		res.json(task);
	})
};

exports.addToCollectedResumes = function(req, res) {
	Recruiter.update(
		{ _id: req.params.recruiterId }, 
		{ $push: { resumes: req.body.resumeId }}, function(err, task) {
			if(err) res.send(err);
			res.json(task);
		});
};