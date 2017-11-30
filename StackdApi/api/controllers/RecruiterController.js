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

// tagID 5a1f6c34045fd70e2cf74dbf
//       5a1f776d605f530f26814e06
// resumeID 5a1f773a5ce0c30f1e6bf71a
// companyID 5a1f781bf831b10f2a17001f
// recruiterID 5a1f7882f831b10f2a170020